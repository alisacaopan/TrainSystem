package com.caopan.TrainSys.controller;

import com.caopan.TrainSys.biz.service.VideoCourseService;
import com.caopan.TrainSys.constant.FilePath;
import com.caopan.TrainSys.model.VideoCourse;
import com.caopan.TrainSys.model.upLoadResult;
import com.caopan.TrainSys.utils.FFMPEG;
import com.caopan.TrainSys.utils.VideoConverTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.io.File;



@RestController
public class VideoCourseController {
    @Autowired
    private VideoCourseService vCourseService;

    private  String root=System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"+
            File.separator+"resources"+File.separator+"static"+File.separator;


    @PostMapping(value = "/insertvCourse")
    public Integer insert(@RequestBody VideoCourse vCourse) {
        int index = 0;
        try {
            if (vCourseService.insert(vCourse) == 1) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            return index;
        }
    }

    @PostMapping(value = "/updatevCourse")
    public Integer update(@RequestBody VideoCourse vCourse) {
        int index = 0;
        try {
            if (vCourseService.update(vCourse) == 1) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            return index;
        }
    }

    @GetMapping(value = "/deletevCourse")
    public Integer delete(@RequestParam("vCourseId") Long vCourseId) {
        int index = 0;
        try {
            if (vCourseService.delete(vCourseId) == 1) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            return index;
        }
    }

    @GetMapping(value = "/getCourseByClassifyId")
    public List<VideoCourse> getvCourseByClassifyId(@RequestParam("classifyId") Integer classifyId) {
        int index = 0;
        try {
            if (vCourseService.getvCourseByClassifyId(classifyId).size() > 0) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            if (index == 1) {
                return vCourseService.getvCourseByClassifyId(classifyId);
            } else {
                return null;
            }
        }
    }

    @GetMapping(value = "/getAllvCourses")
    public List<VideoCourse> getAllvCourses() {
        return vCourseService.getAllvCourses();
    }


    @PostMapping("/uploadVideo")
    public upLoadResult uploadVideo(@RequestParam("videoName") String name,
                                    @RequestParam("videoIntroduce") String introduce,
                                    @RequestParam("file-input") MultipartFile file,
                                    HttpServletRequest req) throws IOException {
        System.out.println("进入addVideo视频上传控制层");
        if (file.getSize() != 0) {
            //上传的多格式的视频文件-作为临时路径保存，转码以后删除-路径不能写//
            String path = root + FilePath.VIDEO_FOLDER;
            String Mp4path1 = root + FilePath.TARGET_FOLDER;
            File TempFile = new File(path);
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
            // 获取上传时候的文件名
            String filename = file.getOriginalFilename();
            // 获取文件后缀名
            String filename_extension = filename.substring(filename
                    .lastIndexOf(".") + 1);
            System.out.println("视频的后缀名:" + filename_extension);

            //时间戳做新的文件名，避免中文乱码-重新生成filename
            long filename1 = new Date().getTime();
            filename = Long.toString(filename1) + "." + filename_extension;
            //去掉后缀的文件名
            String filename2 = filename.substring(0, filename.lastIndexOf("."));
            System.out.println("视频名为:" + filename2);

            //源视频地址+重命名后的视频名+视频后缀
            String yuanPATH = (path +File.separator+ filename);

            System.out.println("视频的完整文件名1:" + filename);
            System.out.println("源视频路径为:" + yuanPATH);

            vCourseService.VideoSubmit(filename_extension,file,filename,path,Mp4path1);

            // 对视频进行转码
            // 测试用
            if (filename_extension.equals("avi") || filename_extension.equals("rm")
                    || filename_extension.equals("rmvb") || filename_extension.equals("wmv")
                    || filename_extension.equals("3gp") || filename_extension.equals("mov")
                    || filename_extension.equals("flv") || filename_extension.equals("ogg")

            ) {

                VideoConverTest c = new VideoConverTest();
                c.run(yuanPATH);   //调用转码
                System.out.println("=================转码过程彻底结束=====================");
            }

            //获取转码后的mp4文件名
            String Mp4path = root+FilePath.TARGET_FOLDER;
            filename2 = filename2 + ".mp4";
            String NewVideopath = Mp4path + File.separator + filename2;
            System.out.println("新视频的url:" + NewVideopath);

            //删除临时文件
            File file2 = new File(path);
            if (!file2.exists()) {
                System.out.println("没有该文件");
            }
            if (!file2.isDirectory()) {
                System.out.println("没有该文件夹");
            }
            String[] tempList = file2.list();
            File temp = null;
            for (int i = 0; i < tempList.length; i++) {
                if (path.endsWith(File.separator)) {
                    temp = new File(path + tempList[i]);
                } else {
                    temp = new File(path + File.separator + tempList[i]);
                }
                if (temp.isFile() || temp.isDirectory()) {
                    temp.delete();        //删除文件夹里面的文件
                }
            }
            System.out.println("所有的临时视频文件删除成功");
            System.out.println(root + FilePath.FFMPEG_PATH);
            System.out.println(root+FilePath.TARGET_FOLDER +File.separator+ filename2);
            System.out.println(root+ FilePath.TARGET_FOLDER_MARK + File.separator + filename2);

            System.out.println(FilePath.MARK_IMAGE);
            HashMap<String, String> dto = new HashMap<String, String>();
            dto.put("ffmpeg_path", root + FilePath.FFMPEG_PATH);//必填
            dto.put("input_path", root+FilePath.TARGET_FOLDER +File.separator+ filename2);//必填
            dto.put("video_converted_path",root+ FilePath.TARGET_FOLDER_MARK + File.separator + filename2);//必填
            dto.put("logo",FilePath.MARK_IMAGE);//可选(注意windows下面的logo地址前面要写4个反斜杠,如果用浏览器里面调用servlet并传参只用两个,如 d:\\:/ffmpeg/input/logo.png)
            FFMPEG secondsString = new FFMPEG();
            secondsString.videoTransfer(dto);
            System.out.println("所有视频文件水印添加成功");
            // 保存到数据库
            VideoCourse vCourse = new VideoCourse();
            vCourse.setName(name);
            vCourse.setIntroduce(introduce);
            vCourse.setAddress(root+ FilePath.TARGET_FOLDER_MARK + File.separator + filename2);
            vCourse.setClassifyId(1);
            vCourseService.insert(vCourse);
        }
        return null;
    }

    @GetMapping(value = "/getOnevCoursesURL")
    public String getvCoursePath(@RequestParam("vCourseId") Long vCourseId){

        String ip = "http://172.20.52.104:8443/TrainSys/";
        ip = ip.replace("\\","/");
        String filePath = vCourseService.getOneCourse(vCourseId).getAddress();
        String relativePath = filePath.replace(root,"");
        //relativePath = relativePath.replace("\\\\","/");
        String path = ip + relativePath;
        path = path.replace("\\","/");
        return path;
    }

}
