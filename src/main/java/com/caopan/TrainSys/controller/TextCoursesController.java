package com.caopan.TrainSys.controller;
import com.caopan.TrainSys.biz.service.TextCourseService;
import com.caopan.TrainSys.constant.FilePath;
import com.caopan.TrainSys.constant.URL;
import com.caopan.TrainSys.model.TextCourse;
import com.caopan.TrainSys.model.VideoCourse;
import com.caopan.TrainSys.model.upLoadResult;
import com.caopan.TrainSys.utils.FFMPEG;
import com.caopan.TrainSys.utils.FileUtil;
import com.caopan.TrainSys.utils.TimeStamp;
import com.caopan.TrainSys.utils.VideoConverTest;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class TextCoursesController {

    @Autowired
    private TextCourseService textCourseService;

    private  String root=System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"+
            File.separator+"resources"+File.separator+"static"+File.separator;

    @GetMapping(value = "/deletetCourse")
    public Integer delete(@RequestParam("tCourseId") Long tCourseId) {
        int index = 0;
        try {
            if (textCourseService.delete(tCourseId) == 1) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            return index;
        }
    }

    public Integer deleteByClassifyId(Integer classifyId) {
        List<TextCourse> textCourses = textCourseService.getTextCourseByClassifyId(classifyId);
        int index = 1;
        try {
            for (int i = 0; i<textCourses.size(); i++){
                textCourseService.delete(textCourses.get(i).gettCourseId());
                index++;
            }
        } catch (Exception e) {
        } finally {
            if(index == textCourses.size()){
                return 1;
            }else {
                return 0;
            }
        }
    }

    @GetMapping(value = "/gettCourseByClassifyId")
    public List<TextCourse> gettCourseByClassifyId(@RequestParam("classifyId") Integer classifyId) {
        int index = 0;
        try {
            if (textCourseService.getTextCourseByClassifyId(classifyId).size() > 0) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            if (index == 1) {
                return textCourseService.getTextCourseByClassifyId(classifyId);
            } else {
                return null;
            }
        }
    }

    @GetMapping("/getAllTextCourses")
    public List<TextCourse> getAllTestCourses() {
        return textCourseService.getTextCourses();
    }

    @PostMapping("/uploadText")
    public String uploadVideo(@RequestParam("classifyId")Integer classifyId,
                                    @RequestParam("fileInput") MultipartFile file,
                                    HttpServletRequest req) throws IOException {
        System.out.println("进入图文课程上传控制层");
        if (file.getSize() != 0) {
            String path = root + FilePath.PDF_FOLDER;
            String targetPath = root + FilePath.TARGET_FOLDER_PDF;
            File TempFile = new File(path);
            if (FileUtil.fileIsEx(path) && FileUtil.fileIsEx(targetPath)) {
                // 获取上传时候的文件名
                String filename = file.getOriginalFilename();
                String filename3 =  filename;
                //TimeStamp.TimeStamp(filename);
                // 获取文件后缀名
                String filename_extension = filename.substring(filename
                        .lastIndexOf(".") + 1);
                System.out.println("图文课程的后缀名:" + filename_extension);

                //时间戳做新的文件名，避免中文乱码-重新生成filename
                long filename1 = new Date().getTime();
                filename = Long.toString(filename1) + "." + filename_extension;
                //去掉后缀的文件名
                String filename2 = filename.substring(0, filename.lastIndexOf("."));
                System.out.println("图文课程名为:" + filename2);

                //源视频地址+重命名后的视频名+视频后缀
                String yuanPATH = (targetPath + File.separator + filename);

                System.out.println("视频的完整文件名1:" + filename);
                System.out.println("源视频路径为:" + yuanPATH);

                textCourseService.VideoSubmit(filename_extension, file, filename, path);

                String finalPath = root + FilePath.PDF_FOLDER;
                filename2 = filename2 + ".pdf";
                String NewPDFpath = finalPath + File.separator + filename2;
                System.out.println("图文课程的url:" + NewPDFpath);

                System.out.println(root + FilePath.PDF_FOLDER + File.separator + filename2);
                System.out.println(root + FilePath.TARGET_FOLDER_PDF + File.separator + filename2);


                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(root + FilePath.TARGET_FOLDER_PDF + File.separator + filename2)));
                Calendar cal = Calendar.getInstance();
                try {
                    textCourseService.setWatermark(bos,
                            root + FilePath.PDF_FOLDER + File.separator + filename2,
                            "123456789");
                } catch (DocumentException e) {
                }
                System.out.println("所有图文文件水印添加成功");
                // 保存到数据库
                TextCourse tCourse = new TextCourse();
                tCourse.setAddress(root + FilePath.TARGET_FOLDER_PDF + File.separator + filename2);
                tCourse.setName(filename3);
                tCourse.setClassifyId(1);
                textCourseService.insert(tCourse);
            }
            return "uploadText";
        } else {
            return null;
        }
    }

    @GetMapping("/getOnetCoursesURL")
    public String getvCoursePath(@RequestParam("tCourseId") Long tCourseId){

        String ip = URL.URL;
        ip = ip.replace("\\","/");
        String filePath = textCourseService.getOnetCourse(tCourseId).getAddress();
        String relativePath = filePath.replace(root,"");
        //relativePath = relativePath.replace("\\\\","/");
        String path = ip + relativePath;
        path = path.replace("\\","/");
        return path;
    }
}
