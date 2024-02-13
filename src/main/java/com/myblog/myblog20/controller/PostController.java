package com.myblog.myblog20.controller;

import com.myblog.myblog20.payload.PostDto;
import com.myblog.myblog20.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//      http://localhost:8080/post/api
@RestController
@RequestMapping("/api/post")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    //      http://localhost:8080/api/post
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    //      http://localhost:8080/api/post/byId?id=1
    @GetMapping("/byId")
    public ResponseEntity<PostDto> getPostById(@RequestParam long id){
        PostDto dto = postService.getPostById(id);
        return new ResponseEntity<>(dto, HttpStatus.FOUND);
    }
    //      http://localhost:8080/api/post/getAll
    //      http://localhost:8080/api/post/getAll?pageNo=0&pageSize=3&sortBy=name&sortDir=desc
    @GetMapping("/getAll")
    public List<PostDto> getAllPost(
            @RequestParam(name="pageNo",required = false,defaultValue = "0") int pageNo,
            @RequestParam(name ="pagSize",required = false,defaultValue = "5") int pageSize,
            @RequestParam(name ="sortBy",required = false,defaultValue = "id") String sortBy,
            @RequestParam(name ="sortDir",required = false,defaultValue = "id") String sortDir
    ){
        List<PostDto> dtos =  postService.getAllPost(pageNo,pageSize,sortBy,sortDir);
        return dtos;
    }
}
