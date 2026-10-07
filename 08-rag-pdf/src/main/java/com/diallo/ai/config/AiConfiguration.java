package com.diallo.ai.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.diallo.ai.assistant.Assistant;
import com.diallo.ai.assistant.ProfileExtractor;

import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;


@Configuration
public class AiConfiguration {


    @Bean
    public ChatModel chatModel() {

        return OllamaChatModel.builder()
                .baseUrl("http://localhost:11434")
                .modelName("llama3.1")
                .temperature(0.3)
                .build();
    }


    
    @Bean
    public Assistant assistant(
            ChatModel chatModel,
            ChatMemoryProvider provider,
            ContentRetriever contentRetriever
    ) {

        return AiServices.builder(Assistant.class)
                .chatModel(chatModel)
                .chatMemoryProvider(provider)
                .contentRetriever(contentRetriever)
                .build();
    }
    
    @Bean
    public ProfileExtractor profileExtractor(ChatModel model) {

        return AiServices.builder(ProfileExtractor.class)
                .chatModel(model)
                .build();

    }
    
//    @Bean
//    public Assistant assistant(
//            ChatModel chatModel,
//            ChatMemory chatMemory
//    ) {
//
//        return AiServices.builder(Assistant.class)
//                .chatModel(chatModel)
//                .chatMemory(chatMemory)
//                .build();
//    }

//    @Bean
//    public Assistant assistant(ChatModel chatModel) {
//
//        return AiServices.create(
//                Assistant.class,
//                chatModel
//        );
//    }

}