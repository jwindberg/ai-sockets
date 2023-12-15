package com.marsraver.messagingstompwebsocket;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
@AllArgsConstructor
public class AnswerController {

	private AiService aiService;

	@MessageMapping("/ask")
	@SendTo("/topic/answers")
	public Response greeting(Request message) throws Exception {
		Thread.sleep(1000); // simulated delay
		String answer = aiService.ask(message.getQuestion());
		return new Response(answer);
	}

}
