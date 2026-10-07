package com.diallo.ai.debug;


import dev.langchain4j.data.segment.TextSegment;


public record SearchResult(

        TextSegment segment,

        double score

) {

}