package com.example.BlogApplication.service;

import com.example.BlogApplication.dto.CommentDto;

public interface CommentService {
    void createComment(String postUrl, CommentDto commentDto);
}
