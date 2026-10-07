package com.diallo.ai.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.diallo.ai.debug.DebugEmbeddingStore;
import com.diallo.ai.debug.SearchResult;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;


@Service
public class SearchService {


    private final EmbeddingModel embeddingModel;

    private final DebugEmbeddingStore embeddingStore;



    public SearchService(
            EmbeddingModel embeddingModel,
            DebugEmbeddingStore embeddingStore) {

        this.embeddingModel = embeddingModel;
        this.embeddingStore = embeddingStore;
    }



    public List<SearchResult> search(
            String question,
            int maxResults) {


        System.out.println();
        System.out.println("==============================");
        System.out.println("Recherche RAG");
        System.out.println("==============================");

        System.out.println(
                "Question : " + question
        );


        /*
         * 1 - Transformer la question
         * en vecteur
         */
        Embedding questionEmbedding =
                embeddingModel
                        .embed(question)
                        .content();


        System.out.println(
                "Embedding question créé"
        );


        /*
         * 2 - Recherche dans notre store
         */
        List<SearchResult> results =
                embeddingStore.search(
                        questionEmbedding,
                        maxResults
                );


        System.out.println(
                "Nombre de résultats : "
                + results.size()
        );



        /*
         * 3 - Affichage debug
         */
        for (SearchResult result : results) {


            System.out.println("------------------------------");

            System.out.println(
                    "Score : "
                    + result.score()
            );


            System.out.println(
                    "Texte : "
                    + result.segment().text()
            );

        }


        return results;
    }

}