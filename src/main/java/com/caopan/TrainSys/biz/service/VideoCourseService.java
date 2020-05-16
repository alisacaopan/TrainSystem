package com.caopan.TrainSys.biz.service;

import com.caopan.TrainSys.biz.dao.TestDao;
import com.caopan.TrainSys.biz.dao.VideoCourseDao;
import com.caopan.TrainSys.model.Question;
import com.caopan.TrainSys.model.Selection;
import com.caopan.TrainSys.model.VideoCourse;
import com.caopan.TrainSys.utils.FFMPEG;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
@EnableAsync
public class VideoCourseService {
    @Autowired
    private VideoCourseDao vcourseDao;

    //名称重复贼不插入
    public Integer insert(VideoCourse vCourse) {
        List<VideoCourse> vcoures = vcourseDao.getvCourse();
        for (int i = 0; i < vcoures.size(); i++) {
            if (vCourse.getName().equals(vcoures.get(i).getName())) {
                return 0;
            }
        }
        vcourseDao.insert(vCourse);
        return 1;
    }

    public Integer update(VideoCourse vCourse) {
        return vcourseDao.update(vCourse);
    }

    public Integer delete(Long vCourseId) {
        return vcourseDao.delete(vCourseId);
    }

    //根据分级取得改分级下所有课程
    public List<VideoCourse> getvCourseByClassifyId(Integer classifyId) {
        return vcourseDao.getvCourseByClassifyId(classifyId);
    }

    public List<VideoCourse> getAllvCourses() {
        return vcourseDao.getvCourse();
    }

    //获得单个视频
    public  VideoCourse getOneCourse(Long vCourseId){return vcourseDao.getOneCourse(vCourseId);}

    //文件上传  参数分别是：后缀名，文件，文件名，临时保存路径,目标路径
    public Integer VideoSubmit(String filename_extension, MultipartFile file,
                               String filename,String path,String Mp4path1){
        if (filename_extension.equals("mp4")) {
            //上传到本地磁盘/服务器
            try {
                System.out.println("写入本地磁盘/服务器");
                InputStream is = file.getInputStream();
                OutputStream os = new FileOutputStream(new File(Mp4path1, filename));
                int len = 0;
                byte[] buffer = new byte[2048];

                while ((len = is.read(buffer)) != -1) {
                    os.write(buffer, 0, len);
                }
                os.close();
                os.flush();
                is.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (filename_extension.equals("avi") || filename_extension.equals("rm")
                || filename_extension.equals("rmvb") || filename_extension.equals("wmv")
                || filename_extension.equals("3gp") || filename_extension.equals("mov")
                || filename_extension.equals("flv") || filename_extension.equals("ogg")
        ) {
            try {
                System.out.println("写入本地磁盘/服务器");
                InputStream is = file.getInputStream();
                OutputStream os = new FileOutputStream(new File(path, filename));
                int len = 0;
                byte[] buffer = new byte[2048];

                while ((len = is.read(buffer)) != -1) {
                    os.write(buffer, 0, len);
                }
                os.close();
                os.flush();
                is.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        System.out.println("========上传完成，开始调用转码工具类=======");
        return 1;
    }

    //异步执行加水印方法
    @Async
    public Integer setWatermark(HashMap<String, String> dto){
        FFMPEG secondsString = new FFMPEG();
        secondsString.videoTransfer(dto);
        System.out.println("所有视频文件水印添加成功");
        return 1;
    }
    public long getVideoDuration(String Mp4path1){
        try {
            Encoder encoder=new Encoder();
            long duration=encoder.getInfo(new File(Mp4path1)).getDuration();
            return duration;
        }catch (EncoderException e){
            e.printStackTrace();
        }
        return 0;
    }

}
