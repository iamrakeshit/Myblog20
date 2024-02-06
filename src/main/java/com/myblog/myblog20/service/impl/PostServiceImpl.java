package com.myblog.myblog20.service.impl;

import com.myblog.myblog20.entity.Post;
import com.myblog.myblog20.exception.ResourseNotFoundException;
import com.myblog.myblog20.payload.PostDto;
import com.myblog.myblog20.repository.PostRepository;
import com.myblog.myblog20.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        post.setName(postDto.getName());
        post.setEmail(postDto.getEmail());
        post.setMobile(postDto.getMobile());

        Post spost = postRepository.save(post);

        PostDto dto = new PostDto();
        dto.setId(spost.getId());
        dto.setName(spost.getName());
        dto.setEmail(spost.getEmail());
        dto.setMobile(spost.getMobile());
        return dto;
    }

    @Override
    public PostDto getPostById(long id) {
       Post post = postRepository.findById(id).orElseThrow(
                ()-> new ResourseNotFoundException("Post not found with id "+ id)
        );
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setName(post.getName());
        dto.setEmail(post.getEmail());
        dto.setMobile(post.getMobile());
        return dto;
    }
}
