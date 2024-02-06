package com.myblog.myblog20.service;

import com.myblog.myblog20.payload.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    PostDto createPost(PostDto postDto);

    PostDto getPostById(long id);

    List<PostDto> getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);
}
