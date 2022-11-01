package com.example.BlogApplication.controller;

import com.example.BlogApplication.dto.PostDto;
import com.example.BlogApplication.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BlogController{
    private PostService postService;

    public BlogController(PostService postService) {
        this.postService = postService;
    }

    //handler method to handle localhost:8080 request
    @GetMapping("/")
    public String viewBlogPost(Model model){
        List<PostDto> postsResponse = postService.findAllPosts();
        model.addAttribute("postsResponse", postsResponse);
        return "blog/view_posts";
    }
}
