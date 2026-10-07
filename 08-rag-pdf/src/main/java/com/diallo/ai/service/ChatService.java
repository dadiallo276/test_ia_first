package com.diallo.ai.service;


import com.diallo.ai.dto.ChatRequest;
import com.diallo.ai.dto.ChatResponse;


public interface ChatService {


    ChatResponse chat(ChatRequest request);

}