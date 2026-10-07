package com.diallo.ai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.diallo.ai.debug.DebugEmbeddingStore;
import com.diallo.ai.loader.PdfLoader;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;

@Service
public class EmbeddingService {

    private final EmbeddingStore<TextSegment> embeddingStore;

    private final EmbeddingModel embeddingModel;

    private final PdfLoader pdfLoader;

    private final DocumentService documentService;

    private final DebugEmbeddingStore debugStore;
    
  

    public EmbeddingService(
            EmbeddingStore<TextSegment> embeddingStore,
            EmbeddingModel embeddingModel,
            PdfLoader pdfLoader,
            DocumentService documentService,
            DebugEmbeddingStore debugStore) {

        this.embeddingStore = embeddingStore;
        this.embeddingModel = embeddingModel;
        this.pdfLoader = pdfLoader;
        this.documentService = documentService;
        this.debugStore = debugStore;
    }

    public void embedPdf(String filename) {

        System.out.println("======================================");
        System.out.println("Chargement du PDF : " + filename);
        System.out.println("======================================");

        /*
         * 1 - Chargement du PDF
         */
        List<Document> documents = pdfLoader.loadPdf(filename);

        if (documents.isEmpty()) {
            throw new RuntimeException("Aucun document chargé.");
        }

        Document document = documents.get(0);

        System.out.println("Document chargé avec succès.");

        /*
         * 2 - Découpage du document
         */
        List<TextSegment> segments = documentService.split(document);

        System.out.println("Nombre de segments créés : " + segments.size());

        int numeroSegment = 1;

        /*
         * 3 - Pour chaque segment
         */
        for (TextSegment segment : segments) {

            System.out.println();
            System.out.println("======================================");
            System.out.println("Segment " + numeroSegment++);
            System.out.println("======================================");

            System.out.println(segment.text());

            /*
             * 4 - Création de l'embedding
             */
            Embedding embedding =
                    embeddingModel
                            .embed(segment)
                            .content();

            float[] vector = embedding.vector();

            System.out.println();
            System.out.println("Dimension du vecteur : " + vector.length);

            System.out.print("Premières valeurs : ");

            for (int i = 0; i < Math.min(10, vector.length); i++) {
                System.out.printf("%.5f ", vector[i]);
            }

            System.out.println();

            /*
             * 5 - Sauvegarde dans le Vector Store
             */
            embeddingStore.add(
                    embedding,
                    segment
            );
            
//            myEmbeddingStore.add(
//            	    new MyEmbedding(segment, embedding));


            /*
             * 6 - Sauvegarde pour le Debug
             */
            debugStore.add(
                    segment,
                    embedding
            );

            System.out.println("Segment ajouté dans le Vector Store.");
        }

        System.out.println();
        System.out.println("======================================");
        System.out.println("Indexation terminée");
        System.out.println("Nombre total de segments : " + debugStore.size());
        System.out.println("======================================");
    }
}

