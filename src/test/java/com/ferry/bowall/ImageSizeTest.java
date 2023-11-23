package com.ferry.bowall;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@SpringBootTest
public class ImageSizeTest {
    @Test
    public void main() {
        try {
            File imageFile = new File("C:\\Users\\Ai_Ti\\Pictures\\7e5d20a4462309f7bf5b5e7f650e0cf3d7cad6ba.jpg");
            BufferedImage image = ImageIO.read(imageFile);
            int width = image.getWidth();
            int height = image.getHeight();
            System.out.println("Image width: " + width);
            System.out.println("Image height: " + height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

