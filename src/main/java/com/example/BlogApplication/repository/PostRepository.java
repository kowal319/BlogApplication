package com.example.BlogApplication.repository;

import com.example.BlogApplication.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByUrl(String url);
    @Query("Select p from Post p WHERE "+
            " p.title LIKE CONCAT('%', :query, '%') OR " +
            " p.shortDescription LIKE CONCAT('%', :query, '%')")
    List<Post> searchPosts(String query);

    @Query(value = "Select * from posts p where p.created_by =:userId", nativeQuery = true)
    List<Post> findPostByUser(Long userId);
}
