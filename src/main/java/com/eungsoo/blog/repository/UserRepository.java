package com.eungsoo.blog.repository;


import com.eungsoo.blog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
//    유저 중복검사 작업 중
//    boolean existsByUsername(String username);
}