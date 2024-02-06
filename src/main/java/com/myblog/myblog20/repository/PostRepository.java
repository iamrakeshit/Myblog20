package com.myblog.myblog20.repository;

import com.myblog.myblog20.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
