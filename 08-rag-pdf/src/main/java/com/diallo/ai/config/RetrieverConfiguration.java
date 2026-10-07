package com.diallo.ai.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;


@Configuration
public class RetrieverConfiguration {


    @Bean
    public EmbeddingStoreContentRetriever contentRetriever(
            EmbeddingStore<TextSegment> embeddingStore,
            EmbeddingModel embeddingModel
    ) {


        return EmbeddingStoreContentRetriever.builder()

                /*
                 * Le magasin contenant les documents vectorisés
                 */
                .embeddingStore(embeddingStore)


                /*
                 * Modèle utilisé pour vectoriser
                 * la question utilisateur
                 */
                .embeddingModel(embeddingModel)


                /*
                 * Nombre de morceaux retournés
                 */
                .maxResults(3)


                /*
                 * Score minimum de similarité
                 */
                .minScore(0.6)


                .build();

    }

}