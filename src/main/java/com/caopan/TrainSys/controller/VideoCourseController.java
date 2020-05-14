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

    private  String root=System.getProperty("user.dir")+File.separator+"src"+File.separator;

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
    public upLoadResult uploadVideo(@RequestParam("file-input") MultipartFile file, HttpServletRequest req) throws IOException {
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
            String yuanPATH = (path + File.separator + filename);

            System.out.println("视频的完整文件名1:" + filename);
            System.out.println("源视频路径为:" + yuanPATH);

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


            HashMap<String, String> dto = new HashMap<String, String>();
            dto.put("ffmpeg_path", FilePath.FFMPEG_PATH);//必填
            dto.put("input_path", Mp4path+ File.separator + filename2);//必填
            dto.put("video_converted_path",root+ FilePath.TARGET_FOLDER_MARK+ File.separator  + filename2);//必填
            dto.put("logo", FilePath.MARK_IMAGE);//可选(注意windows下面的logo地址前面要写4个反斜杠,如果用浏览器里面调用servlet并传参只用两个,如 d:\\:/ffmpeg/input/logo.png)
            FFMPEG secondsString = new FFMPEG();
            secondsString.videoTransfer(dto);
            System.out.println("所有视频文件水印添加成功");
            // 保存到数据库
            /*String finalpath = targetfolder2 + filename2;
            VideoCourse videoCourse = new VideoCourse();
            VideoCourseService videoCourseService = new VideoCourseService();


            videoCourse.setName(videoname);
            //videoCourse.setvCourseId(filename);
            videoCourse.setAddress(finalpath);
            videoCourse.setIntroduce(context);
            videoCourse.setClassifyId(0);
            //已转码后的视频存放地址
            videoCourseService.uplaodvideo(videoCourse);*/
            /*// 实现对数据的更新
            int n = 0;
            n = videoCourseService.uplaodvideo(videoCourse);

            if (n != 0) {
                return new ModelAndView("back/public/success").addObject(
                        "notice", "resourceList?uid=" + uid
                                + "&grade=-1&state=-1&subclass=" + subclass);
            } else {
                return new ModelAndView("back/public/fail").addObject("notice",
                        "resourceList?uid=" + uid
                                + "&grade=-1&state=-1&subclass=" + subclass);
          }*/
        }
        return null;
    }

    @GetMapping(value = "/getOnevCoursesURL")
    public String getvCoursePath(@RequestParam("vCourseId") Long vCourseId){
        System.out.printf("--------------开始--------------");
        String ip = "http://localhost:8443/";
        String filePath = vCourseService.getOneCourse(vCourseId).getAddress();
        String relativePath = filePath.replace(root,"");
        relativePath = relativePath.replace("\\\\","/");
        String path = ip + filePath;
        return path;
    }

}
