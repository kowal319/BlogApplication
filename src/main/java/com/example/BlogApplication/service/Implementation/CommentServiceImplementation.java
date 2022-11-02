package com.example.BlogApplication.service.Implementation;

import com.example.BlogApplication.dto.CommentDto;
import com.example.BlogApplication.entity.Comment;
import com.example.BlogApplication.entity.Post;
import com.example.BlogApplication.mapper.CommentMapper;
import com.example.BlogApplication.repository.CommentRepository;
import com.example.BlogApplication.repository.PostRepository;
import com.example.BlogApplication.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImplementation implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImplementation(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void createComment(String postUrl, CommentDto commentDto) {
        Post post = postRepository.findByUrl(postUrl).get();
        Comment comment = CommentMapper.mapToComment(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);


    }
}
