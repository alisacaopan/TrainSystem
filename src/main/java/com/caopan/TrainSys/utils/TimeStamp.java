package com.caopan.TrainSys.utils;

import java.util.Date;

public class TimeStamp {
    public static String TimeStamp(String filename){
        // 获取文件后缀名
        String filename_extension = filename.substring(filename
                .lastIndexOf(".") + 1);
        String filename2;
        //时间戳做新的文件名，避免中文乱码-重新生成filename
        long filename1 = new Date().getTime();
        filename2 = Long.toString(filename1) + "." + filename_extension;
        return filename2;
    }
}
