package com.example.BlogApplication.controller;

import com.example.BlogApplication.dto.CommentDto;
import com.example.BlogApplication.dto.PostDto;
import com.example.BlogApplication.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

    //handler method to handle view post request
    @GetMapping("/post/{postUrl}")
    public String showPost(@PathVariable("postUrl") String postUrl,
                           Model model){
        PostDto post = postService.findPostByUrl(postUrl);
        CommentDto commentDto = new CommentDto();
        model.addAttribute("post", post);
        model.addAttribute("comment", commentDto);
        return "blog/blog_post";
    }

    //handler method to handle blog search request
    @GetMapping("/page/search")
    public String searchPosts(@RequestParam(value = "query") String query,
                              Model model){
        List<PostDto> postsResponse = postService.searchPosts(query);
        model.addAttribute("postsResponse", postsResponse);
        return "blog/view_posts";
    }


}
