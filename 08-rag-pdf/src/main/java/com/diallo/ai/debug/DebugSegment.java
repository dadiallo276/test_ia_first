package com.diallo.ai.debug;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;

public class DebugSegment {

    private final TextSegment segment;
    private final Embedding embedding;

    public DebugSegment(TextSegment segment, Embedding embedding) {
        this.segment = segment;
        this.embedding = embedding;
    }

    public TextSegment getSegment() {
        return segment;
    }

    public Embedding getEmbedding() {
        return embedding;
    }
}