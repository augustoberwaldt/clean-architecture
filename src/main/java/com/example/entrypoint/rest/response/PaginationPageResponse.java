package com.example.entrypoint.rest.response;

import java.util.List;

public class PaginationPageResponse<T> {
    private List<T> content;
    private String nextPagingState;

    public PaginationPageResponse(List<T> content, String nextPagingState) {
        this.content = content;
        this.nextPagingState = nextPagingState;
    }

    public List<T> getContent() {
        return content;
    }

    public String getNextPagingState() {
        return nextPagingState;
    }
}

