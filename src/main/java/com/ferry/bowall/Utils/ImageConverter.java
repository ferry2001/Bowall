package com.ferry.bowall.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 图片转换工具
 */
public class ImageConverter {

    public static void converter(String pathName, String outputName) {
        /**
         * imagePath 原文件路径以及文件名
         * outputImagePath 输出文件路径以及文件名
         */
        String imagePath = pathName;
        String outputImagePath = outputName;

        //开始将图片转换为png格式
        try {
            // Read the image file
            BufferedImage image = ImageIO.read(new File(imagePath));

            // Create output file with JPEG format
            File outputFile = new File(outputImagePath);

            // Write the image as JPEG to the output file
            ImageIO.write(image, "png", outputFile);

        } catch (IOException e) {
            System.out.println("Error occurred during image conversion: " + e.getMessage());
        }

        /**
         * 将转换前的图片删除
         */
        File file = new File(imagePath);
        if (file.exists()) {
            if (file.delete()) {
            } else {
                System.out.println("Failed to delete the file.");
            }
        } else {
            System.out.println("File does not exist.");
        }
    }
}