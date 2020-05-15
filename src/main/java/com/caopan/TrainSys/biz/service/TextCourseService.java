package com.caopan.TrainSys.biz.service;

import com.caopan.TrainSys.biz.dao.TextCourseDao;
import com.caopan.TrainSys.model.TextCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;


import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

import java.io.*;
import java.util.List;

@Service
public class TextCourseService {
    @Autowired
    private TextCourseDao textCourseDao;

    private  String root=System.getProperty("user.dir");

    public Integer insert(TextCourse tCourse){return textCourseDao.insert(tCourse);};

    public List<TextCourse> getTextCourses() {
        return textCourseDao.getTextCourse();
    }

    //文件上传  参数分别是：后缀名，文件，文件名，临时保存路径
    public Integer VideoSubmit(String filename_extension, MultipartFile file,
                               String filename,String path){
        if (filename_extension.equals("pdf")) {
            //上传到本地磁盘/服务器
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
            System.out.println("========上传完成=======");
            return 1;
        } else {
            return 0;
        }
    }

    /**
     *
     * @param bos输出文件的位置
     * @param input
     *            原PDF位置
     * @param waterMarkName
     *            页脚添加水印
     * @param permission
     *            权限码
     * @throws DocumentException
     * @throws IOException
     */
    public void setWatermark(BufferedOutputStream bos, String input, String waterMarkName)
            throws DocumentException, IOException {
        root = root.replace("\\","/");
        PdfReader reader = new PdfReader(input);
        PdfStamper stamper = new PdfStamper(reader, bos);
        int total = reader.getNumberOfPages() + 1;
        PdfContentByte content;
        BaseFont base = BaseFont.createFont(root+"/SIMYOU.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        PdfGState gs = new PdfGState();
        for (int i = 1; i < total; i++) {
            //content = stamper.getOverContent(i);// 在内容上方加水印
            content = stamper.getUnderContent(i);//在内容下方加水印
            gs.setFillOpacity(0.2f);
            // content.setGState(gs);
            content.beginText();
            content.setColorFill(Color.LIGHT_GRAY);
            content.setFontAndSize(base, 25);
            content.setTextMatrix(70, 200);
            content.showTextAligned(Element.ALIGN_CENTER, "这里是水印", 300, 350, 55);
            //Image image = Image.getInstance(FilePath.MARK_IMAGE);
            /*
              img.setAlignment(Image.LEFT | Image.TEXTWRAP);
              img.setBorder(Image.BOX); img.setBorderWidth(10);
              img.setBorderColor(BaseColor.WHITE); img.scaleToFit(100072);//大小
              img.setRotationDegrees(-30);//旋转
             */
            //image.setAbsolutePosition(200, 206); // set the first background
            // image of the absolute
            //image.scaleToFit(200, 200);
            //content.addImage(image);
            content.setColorFill(Color.BLACK);
            content.setFontAndSize(base, 8);
            content.showTextAligned(Element.ALIGN_CENTER, "下载时间：" + waterMarkName + "", 300, 10, 0);
            content.endText();
        }
        stamper.close();
    }

    public List<TextCourse> getTextCourseByClassifyId(Integer classifyId) {
        return textCourseDao.getTextCourseByClassifyId(classifyId);
    }

    public  TextCourse getOnetCourse(Long tCourseId){
        return textCourseDao.getOnetCourse(tCourseId);
    }
}
