package com.diallo.ai.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diallo.ai.dto.ChatRequest;
import com.diallo.ai.dto.ChatResponse;
import com.diallo.ai.service.ChatService;


@RestController
@RequestMapping("/api/chat")
public class ChatController {


    private final ChatService chatService;


    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }



    @PostMapping
    public ChatResponse chat(
            //@Valid 
            @RequestBody ChatRequest request
    ) {

        return chatService.chat(request);

    }

}