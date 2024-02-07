package com.myblog.myblog20.service.impl;

import com.myblog.myblog20.entity.Comment;
import com.myblog.myblog20.entity.Post;
import com.myblog.myblog20.exception.ResourseNotFoundException;
import com.myblog.myblog20.payload.CommentDto;
import com.myblog.myblog20.repository.CommentRepository;
import com.myblog.myblog20.repository.PostRepository;
import com.myblog.myblog20.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private ModelMapper modelMapper;

    public CommentServiceImpl(PostRepository postRepository,CommentRepository commentRepository,ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
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

    @Override
    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public CommentDto updateComment(long id, CommentDto commentDto, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourseNotFoundException("post not found found with id "+ id)
        );
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()->new ResourseNotFoundException("post not found found with id "+ id)
        );
        Comment c = mapToEntity(commentDto);
        c.setId(comment.getId());
        c.setPost(post);
        Comment savedComment = commentRepository.save(c);
        return mapToDto(savedComment);
    }

    CommentDto mapToDto(Comment comment){
        CommentDto dto = modelMapper.map(comment, CommentDto.class);
        return dto;
    }

    Comment mapToEntity(CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }
}
