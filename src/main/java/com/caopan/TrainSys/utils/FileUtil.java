package com.caopan.TrainSys.utils;

import org.springframework.stereotype.Service;

import java.io.File;
@Service
public  class FileUtil {
    public static boolean fileIsEx(String filePath){
        try{
            File TempFile = new File(filePath);
            if (TempFile.exists()) {
                if (TempFile.isDirectory()) {
                    System.out.println("该文件夹存在。");
                } else {
                    System.out.println("同名的文件存在，不能创建文件夹。");
                }
            } else {
                System.out.println("文件夹不存在，创建该文件夹。");
                TempFile.mkdir();
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

       return true;
    }
}
