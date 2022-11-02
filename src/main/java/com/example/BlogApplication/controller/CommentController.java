package com.example.BlogApplication.controller;

import com.example.BlogApplication.dto.CommentDto;
import com.example.BlogApplication.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postUrl}/comments")
    public String createComment(@PathVariable("postUrl") String postUrl,
                                @ModelAttribute("comment") CommentDto commentDto,
                                Model model){
        commentService.createComment(postUrl, commentDto);
        return "redirect:/post/" + postUrl;
    }
}
