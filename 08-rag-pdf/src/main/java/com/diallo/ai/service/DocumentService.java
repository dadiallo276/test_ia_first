package com.diallo.ai.service;


import java.util.List;

import org.springframework.stereotype.Service;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;



@Service
public class DocumentService {


    /**
     * Découpe un document en morceaux utilisables par le RAG
     */
    public List<TextSegment> split(Document document) {


        return DocumentSplitters
                .recursive(
                        500,
                        50
                )
                .split(document);

    }

}