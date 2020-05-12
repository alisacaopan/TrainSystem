package com.caopan.TrainSys.utils;

import java.io.File;

public class FileSave {
    public boolean saveFile(String target, String source) {
        File TempFile = new File(target);
        if (TempFile.exists()) {
            if (TempFile.isDirectory()) {
                System.out.println("该文件夹存在。");
            } else {
                System.out.println("同名的文件存在，不能创建文件夹。");
                return false;
            }
        } else {
            System.out.println("文件夹不存在，创建该文件夹。");
            TempFile.mkdir();
        }


        return true;

    }


    private boolean isTargetexist(String target) {

		File file = new File(target);
		try {
			if (file.exists()) {
				System.out.println("视频文件不存在============="+target);
				return true;
			} else {
				System.out.println("视频文件存在"+target);
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("拒绝对文件进行读访问");
		}
		return false;
	}

}
