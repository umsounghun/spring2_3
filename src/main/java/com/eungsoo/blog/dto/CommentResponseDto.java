package com.eungsoo.blog.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CommentResponseDto {
    private String name;
    private String comments;
    private LocalDateTime modifiedAt;
    private Long userId;
    private Boolean writeByMe;

    public CommentResponseDto(String name, String comments, LocalDateTime modifiedAt, Long userId, Boolean writeByMe) {
        this.name = name;
        this.comments = comments;
        this.modifiedAt = modifiedAt;
        this.userId = userId;
        this.writeByMe = writeByMe;
    }
}
