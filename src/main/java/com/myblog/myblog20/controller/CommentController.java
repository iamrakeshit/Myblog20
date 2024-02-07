package com.myblog.myblog20.controller;

import com.myblog.myblog20.payload.CommentDto;
import com.myblog.myblog20.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//      http://localhost:8080/comment/api
@RestController
@RequestMapping("/comment/api")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //      http://localhost:8080/comment/api?postId=1
    @PostMapping
    public ResponseEntity<CommentDto>createComment(@RequestBody CommentDto commentDto, @RequestParam long postId){
        CommentDto cdto = commentService.createComment(commentDto, postId);
        return  new ResponseEntity<>(cdto, HttpStatus.CREATED);
    }
}
