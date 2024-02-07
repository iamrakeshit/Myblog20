package com.myblog.myblog20.service.impl;

import com.myblog.myblog20.entity.Comment;
import com.myblog.myblog20.entity.Post;
import com.myblog.myblog20.exception.ResourseNotFoundException;
import com.myblog.myblog20.payload.CommentDto;
import com.myblog.myblog20.repository.CommentRepository;
import com.myblog.myblog20.repository.PostRepository;
import com.myblog.myblog20.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public CommentServiceImpl(PostRepository postRepository,CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
       Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourseNotFoundException("post not found with id "+postId)
        );
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        CommentDto dto = new CommentDto();
        dto.setId(savedComment.getId());
        dto.setContent(savedComment.getContent());
        return dto;
    }
}
