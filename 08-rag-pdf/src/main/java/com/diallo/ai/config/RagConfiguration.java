package com.diallo.ai.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;


@Configuration
public class RagConfiguration {


    @Bean
    public EmbeddingStore<TextSegment> embeddingStore() {

        return new InMemoryEmbeddingStore<>();

    }

}