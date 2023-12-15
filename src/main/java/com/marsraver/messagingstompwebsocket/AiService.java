package com.marsraver.messagingstompwebsocket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.client.AiClient;
import org.springframework.ai.client.AiResponse;
import org.springframework.ai.client.Generation;
import org.springframework.ai.metadata.ChoiceMetadata;
import org.springframework.ai.prompt.Prompt;
import org.springframework.ai.prompt.messages.ChatMessage;
import org.springframework.ai.prompt.messages.Message;
import org.springframework.ai.prompt.messages.MessageType;
import org.springframework.ai.prompt.messages.UserMessage;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class AiService {
    private AiClient aiClient;

    private List<BackAndForth> backAndForths = new LinkedList<>();

    public String ask(String message) {
        log.debug("Message: " + message);

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
