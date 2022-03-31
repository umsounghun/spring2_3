package com.eungsoo.blog.models;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String name;
    private String comments;
    private Long contentsId;
}