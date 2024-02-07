package com.myblog.myblog20.service.impl;

import com.myblog.myblog20.entity.Post;
import com.myblog.myblog20.exception.ResourseNotFoundException;
import com.myblog.myblog20.payload.PostDto;
import com.myblog.myblog20.repository.PostRepository;
import com.myblog.myblog20.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository,ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post spost = postRepository.save(post);
        PostDto dto = mapToDto(spost);
        return dto;
    }
    PostDto mapToDto(Post post){
        PostDto dto = modelMapper.map(post, PostDto.class);
        return  dto;
    }
    Post mapToEntity(PostDto postDto){
        Post post = modelMapper.map(postDto, Post.class);
        return post;
    }
    @Override
    public PostDto getPostById(long id) {
       Post post = postRepository.findById(id).orElseThrow(
                ()-> new ResourseNotFoundException("Post not found with id "+ id)
        );
       PostDto dto = mapToDto(post);
        return dto;
    }

    @Override
    public List<PostDto> getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);
        Page<Post> pagePost = postRepository.findAll(pageable);
        List<Post> posts = pagePost.getContent();
        List<PostDto> dtos = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        return dtos;
    }
}