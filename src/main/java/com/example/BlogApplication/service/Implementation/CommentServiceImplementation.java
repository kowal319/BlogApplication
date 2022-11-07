package com.example.BlogApplication.service.Implementation;

import com.example.BlogApplication.dto.CommentDto;
import com.example.BlogApplication.entity.Comment;
import com.example.BlogApplication.entity.Post;
import com.example.BlogApplication.entity.User;
import com.example.BlogApplication.mapper.CommentMapper;
import com.example.BlogApplication.repository.CommentRepository;
import com.example.BlogApplication.repository.PostRepository;
import com.example.BlogApplication.repository.UserRepository;
import com.example.BlogApplication.service.CommentService;
import com.example.BlogApplication.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImplementation implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;

    public CommentServiceImplementation(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createComment(String postUrl, CommentDto commentDto) {
        Post post = postRepository.findByUrl(postUrl).get();
        Comment comment = CommentMapper.mapToComment(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);


    }

    @Override
    public List<CommentDto> findAllComments() {
        List<Comment> comments = commentRepository.findAll();

        return comments.stream()
                .map(CommentMapper::mapToCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentDto> findCommentsByPost() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Long userId = createdBy.getId();
        List<Comment> comments = commentRepository.findCommentsByPost(userId);
        return comments.stream().map((comment) -> CommentMapper.mapToCommentDto(comment))
                .collect(Collectors.toList());
    }
}
