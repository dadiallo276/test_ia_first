package com.diallo.ai.config;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.diallo.ai.debug.DebugEmbeddingStore;
import com.diallo.ai.debug.DebugSegment;
import com.diallo.ai.service.EmbeddingService;



@Configuration
public class RagInitializer {


    @Bean
    CommandLineRunner loadDocuments(
            EmbeddingService embeddingService,
            DebugEmbeddingStore debugStore
    ) {


        return args -> {


            System.out.println(
                "================================="
            );

            System.out.println(
                "Chargement des documents RAG..."
            );


            embeddingService.embedPdf(
                    "glossaire_informatique.pdf"
            );


            System.out.println(
                "Documents chargés avec succès"
            );


            System.out.println("=========== EMBEDDING STORE ===========");
            System.out.println("Nombre de segments : " + debugStore.size());

            int i = 1;

            for (DebugSegment item : debugStore.getAll()) {

                System.out.println();
                System.out.println("--------------------------------");
                System.out.println("Segment " + i++);
                System.out.println("--------------------------------");

                System.out.println(item.getSegment().text());

                float[] vector = item.getEmbedding().vector();

                System.out.println();
               // System.out.println("Dimension : " + vector.length);

                System.out.print("Début du vecteur : ");

//                for (int j = 0; j < Math.min(10, vector.length); j++) {
//                    System.out.printf("%.5f ", vector[j]);
//                }

                System.out.println();
            }

            System.out.println("=======================================");

        };

    }

}