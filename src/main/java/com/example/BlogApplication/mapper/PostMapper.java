package com.example.BlogApplication.mapper;

import com.example.BlogApplication.dto.PostDto;
import com.example.BlogApplication.entity.Post;

public class PostMapper {
    //map Post entity to PostDto
    public PostDto mapToPostDto(Post post){
        return PostDto.builder().
                id(post.getId())
                .title(post.getTitle())
                .url(post.getUrl())
                .content(post.getContent())
                .shortDescription(post.getShortDescription())
                .createdOn(post.getCreatedOn())
                .updatedOn(post.getUpdatedOn())
                .build();
    }
}
