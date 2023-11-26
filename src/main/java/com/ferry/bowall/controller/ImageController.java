package com.ferry.bowall.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ferry.bowall.Utils.ImageConverter;
import com.ferry.bowall.common.R;
import com.ferry.bowall.entity.Image;
import com.ferry.bowall.entity.User;
import com.ferry.bowall.service.ImageService;
import com.ferry.bowall.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RequestMapping("/image")
@RestController
@Slf4j
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping("/getImage")
    public R<List<Image>> getImage(@RequestParam String account) {
        LambdaQueryWrapper<Image> imageLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Image> images = imageLambdaQueryWrapper.eq(Image::getAccount, account);
        List<Image> img = imageService.list(images);
        return R.success(img);
    }

    /**
     * post the image
     * @param images
     * @return
     * @throws IOException
     */
    @PostMapping("/post")
    public R<String> post(@RequestParam MultipartFile images,String account,String postId) throws IOException {
        UUID uuid = UUID.randomUUID();
        String pathName = "/usr/local/demo/images/" + uuid;
        String outputName = "/usr/local/demo/images/" + uuid + ".png";
        String url = "http://47.98.224.129:8080/images/" + uuid + ".png";
   /*     String pathName = "D:\\Java\\bowall\\src\\main\\resources\\images\\" + uuid;
        String outputName = "D:\\Java\\bowall\\src\\main\\resources\\images\\" + uuid + ".png";
        String url = "http://localhost:8080/images/" + uuid + ".png";*/
        images.transferTo(new File(pathName));
        ImageConverter.converter(pathName, outputName);
        BufferedImage bufferedImage = ImageIO.read(new File(outputName));

        Image image = new Image();
        image.setAccount(account);
        image.setUrl(url);
        image.setPostsId(postId);
        image.setWidth(String.valueOf(bufferedImage.getWidth()));
        image.setHeight(String.valueOf(bufferedImage.getHeight()));
        image.setUpdateDate(LocalDateTime.now());
        imageService.save(image);

        return R.success("传输成功");
    }


}
