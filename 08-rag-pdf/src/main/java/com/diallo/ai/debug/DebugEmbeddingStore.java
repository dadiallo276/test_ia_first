package com.diallo.ai.debug;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.diallo.ai.vector.CosineSimilarity;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;


@Component
public class DebugEmbeddingStore {


    private final List<DebugSegment> data = new ArrayList<>();


    public void add(
            TextSegment segment,
            Embedding embedding) {

        data.add(
            new DebugSegment(segment, embedding)
        );
    }



    public List<DebugSegment> getAll() {

        return data;
    }



    public int size() {

        return data.size();
    }



    /**
     * Recherche vectorielle simple
     */
    public List<SearchResult> search(
            Embedding queryEmbedding,
            int maxResults) {


        List<SearchResult> results =
                new ArrayList<>();


        for (DebugSegment item : data) {


            double score =
                CosineSimilarity.calculate(
                    queryEmbedding.vector(),
                    item.getEmbedding().vector()
                );


            results.add(
                new SearchResult(
                    item.getSegment(),
                    score
                )
            );
        }


        return results.stream()

                .sorted(
                    (a,b) ->
                    Double.compare(
                        b.score(),
                        a.score()
                    )
                )

                .limit(maxResults)

                .toList();
    }

}