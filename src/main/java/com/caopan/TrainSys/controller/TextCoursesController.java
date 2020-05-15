package com.caopan.TrainSys.controller;
import com.caopan.TrainSys.biz.service.TextCourseService;
import com.caopan.TrainSys.constant.FilePath;
import com.caopan.TrainSys.model.TextCourse;
import com.caopan.TrainSys.model.VideoCourse;
import com.caopan.TrainSys.model.upLoadResult;
import com.caopan.TrainSys.utils.FFMPEG;
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

    @GetMapping("/getAllTextCourses")
    public List<TextCourse> getAllTestCourses() {
        return textCourseService.getTextCourses();
    }

    @PostMapping("/uploadVideo")
    public upLoadResult uploadVideo(@RequestParam("file-input") MultipartFile file,
                                    HttpServletRequest req) throws IOException {
        System.out.println("进入图文课程上传控制层");
        if (file.getSize() != 0) {
            String path = root + FilePath.PDF_FOLDER;
            String targetPath = root + FilePath.TARGET_FOLDER_PDF;
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
            System.out.println("图文课程的后缀名:" + filename_extension);

            //时间戳做新的文件名，避免中文乱码-重新生成filename
            //long filename1 = new Date().getTime();
            //filename = Long.toString(filename1) + "." + filename_extension;
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
            }catch (DocumentException e){}
            System.out.println("所有图文文件水印添加成功");
            // 保存到数据库
            TextCourse tCourse = new TextCourse();
            tCourse.setAddress(root+ FilePath.TARGET_FOLDER_PDF + File.separator + filename2);
            tCourse.setName(filename2);
            tCourse.setClassifyId(1);
            textCourseService.insert(tCourse);
        }
        return null;
    }

    @GetMapping(value = "/getOnetCoursesURL")
    public String getvCoursePath(@RequestParam("tCourseId") Long tCourseId){

        String ip = "http://172.20.52.104:8443/TrainSys/";
        ip = ip.replace("\\","/");
        String filePath = textCourseService.getOnetCourse(tCourseId).getAddress();
        String relativePath = filePath.replace(root,"");
        //relativePath = relativePath.replace("\\\\","/");
        String path = ip + relativePath;
        path = path.replace("\\","/");
        return path;
    }
}
