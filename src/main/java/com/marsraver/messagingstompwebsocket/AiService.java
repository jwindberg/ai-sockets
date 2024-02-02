package com.marsraver.messagingstompwebsocket;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.client.AiClient;
import org.springframework.ai.client.AiResponse;
import org.springframework.ai.client.Generation;
import org.springframework.ai.prompt.Prompt;
import org.springframework.ai.prompt.messages.ChatMessage;
import org.springframework.ai.prompt.messages.Message;
import org.springframework.ai.prompt.messages.MessageType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AiService {
    private AiClient aiClient;
    private Map<String, List<BackAndForth>> chats = new HashMap<>();

    public AiService(AiClient aiClient) {
        this.aiClient = aiClient;
    }

    public String ask(String chatId, String message) {
        log.debug("Message: " + message);

        if (!chats.containsKey(chatId)) {
            chats.put(chatId, new LinkedList<>());
        }
        List<BackAndForth> backAndForths = chats.get(chatId);

        List<Message> messages = new LinkedList<>();
        backAndForths.forEach(b -> {
            messages.add(new ChatMessage(MessageType.USER, b.getMessage()));
            messages.add(new ChatMessage(MessageType.ASSISTANT, b.getAnswer()));
        });
        messages.add(new ChatMessage(MessageType.USER, message));
        Prompt prompt = new Prompt(messages);

        AiResponse aiResponse = aiClient.generate(prompt);
        Generation generation = aiResponse.getGeneration();
        String answer = generation.getText();
        log.debug("Answer: " + answer);
        backAndForths.add(BackAndForth.builder().message(message).answer(answer)
                .build());
        return answer;
    }

    @Data
    @Builder
    private static class BackAndForth {
        private String message;
        private String answer;
    }

}
