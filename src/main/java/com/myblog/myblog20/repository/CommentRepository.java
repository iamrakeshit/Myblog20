package com.myblog.myblog20.repository;

import com.myblog.myblog20.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
