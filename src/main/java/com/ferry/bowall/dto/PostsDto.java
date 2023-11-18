package com.ferry.bowall.dto;

import com.ferry.bowall.entity.Image;
import com.ferry.bowall.entity.Posts;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PostsDto extends Posts {
    private List<Image> images = new ArrayList<>();
}
