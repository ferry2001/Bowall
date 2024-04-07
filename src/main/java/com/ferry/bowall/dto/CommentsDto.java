package com.ferry.bowall.dto;

import com.ferry.bowall.entity.Comments;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Data;

@Data
public class CommentsDto {
    private Comments comments;
    private String name;
    private String userAvatar;
    private String postsImage;
    private String account;
    private String postId;

}
