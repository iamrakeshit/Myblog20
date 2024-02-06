package com.myblog.myblog20.controller;

import com.myblog.myblog20.payload.PostDto;
import com.myblog.myblog20.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//      http://localhost:8080/post/api
@RestController
@RequestMapping("post/api")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    //      http://localhost:8080/post/api
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    //      http://localhost:8080/post/api/byId?id=1
    @GetMapping("/byId")
    public ResponseEntity<PostDto> getPostById(@RequestParam long id){
        PostDto dto = postService.getPostById(id);
        return new ResponseEntity<>(dto, HttpStatus.FOUND);
    }
    //      http://localhost:8080/post/api/getAll
    @GetMapping("/getAll")
    public List<PostDto> getAllPost(){
        List<PostDto> dtos =  postService.getAllPost();
        return dtos;
    }
}
