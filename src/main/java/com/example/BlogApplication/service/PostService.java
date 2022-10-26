package com.example.BlogApplication.service;

import com.example.BlogApplication.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAllPosts();

    void createPost(PostDto postDto);
}
