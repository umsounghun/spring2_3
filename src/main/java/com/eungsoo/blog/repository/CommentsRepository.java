package com.eungsoo.blog.repository;

import com.eungsoo.blog.models.Comments;
import com.eungsoo.blog.models.Contents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CommentsRepository extends JpaRepository<Comments, Long> {
    List<Comments> findAllByOrderByCreatedAtDesc();
    List<Comments> findByContentsId(Long id);
}