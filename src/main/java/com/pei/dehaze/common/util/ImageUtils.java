package com.pei.dehaze.common.util;

import com.pei.dehaze.common.exception.BusinessException;
import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;

public class ImageUtils {
    public static void generateThumbnail(String srcPath, String destPath, int width, int height) {
       try {
           // 创建目标路径所需的全部目录
           File destDir = new File(destPath).getParentFile();
           if (!destDir.exists()) {
               boolean flag = destDir.mkdirs();
               if (!flag) {
                   throw new BusinessException("创建缩略图目录失败");
               }
           }
           Thumbnails.of(new File(srcPath))
                   .size(width, height)
                   .outputQuality(0.5f)
                   .toFile(new File(destPath));
       } catch (IOException e) {
           e.printStackTrace();
           throw new BusinessException("生成缩略图失败");
       }
    }
}
