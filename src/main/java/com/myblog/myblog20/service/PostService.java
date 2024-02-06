package com.myblog.myblog20.service;

import com.myblog.myblog20.payload.PostDto;
import org.springframework.stereotype.Service;

@Service
public interface PostService {
    PostDto createPost(PostDto postDto);
}
