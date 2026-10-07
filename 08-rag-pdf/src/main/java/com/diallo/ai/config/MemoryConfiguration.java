package com.diallo.ai.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;


@Configuration
public class MemoryConfiguration {


//    @Bean
//    public ChatMemory chatMemory() {
//
//        return MessageWindowChatMemory.builder()
//                .maxMessages(20)
//                .build();
//
//    }
	
	@Bean
	public ChatMemoryProvider chatMemoryProvider() {

	    return memoryId ->
	            MessageWindowChatMemory.withMaxMessages(20);

	}

}