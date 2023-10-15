package com.ferry.bowall.entity;


import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;

@Data
@Qualifier
public class Image {
    private String account;
    private String url;
}
