package com.diallo.ai.loader;


import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentParser;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;


@Component
public class PdfLoader {


    private final DocumentParser parser;


    public PdfLoader() {

        this.parser = new ApachePdfBoxDocumentParser();

    }



    public List<Document> loadPdf(String filename) {


        try {

            var resource =
                    new ClassPathResource("docs/" + filename);


            Path path =
                    resource.getFile().toPath();


            Document document =
                    FileSystemDocumentLoader.loadDocument(
                            path,
                            parser
                    );


            return List.of(document);


        } catch (IOException e) {

            throw new RuntimeException(
                    "Erreur lors du chargement du PDF : "
                    + filename,
                    e
            );
        }

    }

}