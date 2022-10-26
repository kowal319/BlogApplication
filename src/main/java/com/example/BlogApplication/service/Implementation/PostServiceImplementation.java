package com.example.BlogApplication.service.Implementation;

import com.example.BlogApplication.dto.PostDto;
import com.example.BlogApplication.entity.Post;
import com.example.BlogApplication.mapper.PostMapper;
import com.example.BlogApplication.repository.PostRepository;
import com.example.BlogApplication.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImplementation implements PostService {

    private PostRepository postRepository;

    public PostServiceImplementation(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostDto> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createPost(PostDto postDto) {
        Post post = PostMapper.mapToPost(postDto);
        postRepository.save(post);
    }
}
