package com.eungsoo.blog.repository;

import com.eungsoo.blog.models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentsRepository extends JpaRepository<Comments, Long> {
    List<Comments> findAllByOrderByCreatedAtDesc();
    List<Comments> findByContentsId(Long id);
}