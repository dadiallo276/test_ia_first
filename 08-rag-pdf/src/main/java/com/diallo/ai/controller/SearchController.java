package com.diallo.ai.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diallo.ai.debug.SearchResult;
import com.diallo.ai.service.SearchService;

@RestController
@RequestMapping("/search")
public class SearchController {


    private final SearchService searchService;


    public SearchController(
            SearchService searchService) {

        this.searchService = searchService;
    }



    @GetMapping
    public List<SearchResult> search(
            @RequestParam String q) {

        return searchService.search(q,3);
    }

}