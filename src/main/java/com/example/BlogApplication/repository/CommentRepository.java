package com.example.BlogApplication.repository;

import com.example.BlogApplication.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository <Comment, Long> {


    @Query(value = "select c.* from comments c inner join posts p where c.post_id = p.id and p.created_by =:userId ", nativeQuery = true)
    List<Comment> findCommentsByPost(Long userId);
}
