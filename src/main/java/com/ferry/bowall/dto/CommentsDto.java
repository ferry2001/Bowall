package com.ferry.bowall.dto;

import com.ferry.bowall.entity.Comments;
import com.ferry.bowall.entity.User;
import lombok.Data;

@Data
public class CommentsDto {
    private Comments comments;
    private String userName;
    private String avatar;
}
