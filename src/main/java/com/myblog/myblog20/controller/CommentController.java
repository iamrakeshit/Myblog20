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
    //      http://localhost:8080/comment/api/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteComment(@PathVariable long id){
        commentService.deleteComment(id);
        return new ResponseEntity<>("Comment is deleted", HttpStatus.OK);
    }
    //      http://localhost:8080/comment/api/2
    @PutMapping("{id}/post/{postId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long id, @RequestBody CommentDto commentDto,@PathVariable long postId){
        CommentDto dto = commentService.updateComment(id, commentDto, postId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
