package com.caopan.TrainSys.controller;

import com.caopan.TrainSys.biz.service.QuestionService;
import com.caopan.TrainSys.biz.service.SelectionService;
import com.caopan.TrainSys.biz.service.TestService;
import com.caopan.TrainSys.biz.service.UserService;
import com.caopan.TrainSys.constant.FilePath;
import com.caopan.TrainSys.model.Question;
import com.caopan.TrainSys.model.Selection;
import com.caopan.TrainSys.model.Test;
import com.caopan.TrainSys.model.upLoadResult;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;

    @Autowired
    private SelectionService selectionService;

    @PostMapping(value = "/deleteTest")
    public Integer deleteTest(@RequestParam(value = "testId") Long testId) {
        int index = 0;
        try {
            if (testService.delete(testId) == 1) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            return index;
        }
    }

    @PostMapping("/getAnwser")
    public Integer getAnwser(@RequestParam("quesId") Long quesId, @RequestParam("anwser") int[] anwser) {
        int index = 0;
        try {
            //如果答案是正确的 返回1，错误的返回0 ，异常也返回0
            if (testService.getAnwser(quesId, anwser) == 1) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            return index;
        }
    }

    @PostMapping("/getAllAnwser")
    public Integer getAllAnwser(@RequestParam("quesId") List<Long> quesId,
                                @RequestParam("anwsers") List<int[]> anwsers,
                                @RequestParam("userId") Long userId) {
        int index = 0;
        int Grade = 0;
        try {
            Grade = testService.getAllAnwser(quesId, anwsers, userId);
            //如果分数不是0分,返回分数
            if (Grade > 0) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            if (index == 1) {
                return Grade;
            } else {
                return 0;
            }
        }
    }

    @GetMapping("/startTest")
    public List startTest(@RequestParam("vCourseId") int vCourseId) {
        return testService.getQuestionId(vCourseId);
    }

    @GetMapping("/getQuesContent")
    public List<Object> getQuesContent(@RequestParam("quesId") Long quesId) {
        return testService.getContent(quesId);
    }

    @GetMapping("/getQuestionType")
    public String getQuestionType(@RequestParam("quesId") Long quesId) {
        Question question = testService.getQuestionById(quesId);
        return question.getQuesType();
    }

    /**
     * @param openId
     * @param testArray
     * @param vCourseId
     * @return 1表示记录成功 0表示没有记录成功
     */
    @PostMapping("/testrecord")
    public Long testrecord(@RequestParam("openId") String openId,
                           @RequestParam("testArray") long[][] testArray,
                           @RequestParam("vCourseId") Long vCourseId) {
        float Grade = 0;
        int index = 0;
        Long testId = (long) 0;
        Long userId = userService.getUserByOpenId(openId).getId();
        try {
            String testRecord = "";
            for (int i = 0; i < testArray.length / 2; i++) {
                if (testArray[2 * i + 1][0] == (long) 1) {
                    Grade = Grade + 1;
                }
                testRecord = Arrays.deepToString(testArray);
            }
            Grade = Grade / testArray.length;
            Grade = Grade * 2;
            Test test = new Test();
            test.setUserId(userId);
            test.setvCourseId(vCourseId);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            Date date = new Date();
            test.setTestTime(df.format(date));
            test.setGrade(Grade);
            test.setTestRecord(testRecord);
            if (testService.insert(test) == 1) {
                index = 1;
            }
            testId = test.getTestId();
        } catch (Exception e) {
        } finally {
            if (index == 1) {
                return testId;
            } else {
                return (long) 0;
            }

        }
    }

    @GetMapping("/getGradeBytestId")
    public float getGradeBytestId(@RequestParam("testId") long testId) {
        return testService.getTestBytestId(testId).getGrade();
    }


    @PostMapping("/uploadTest")
    public upLoadResult uploadTest(@RequestParam("file-input") MultipartFile file, HttpServletRequest req) throws IOException, InvalidFormatException {

        System.out.println("进入addtests控制层");
upLoadResult upLoadResult=new upLoadResult();
        String path = FilePath.TEST_FOLDER;
        // 获取上传时候的文件名
        String filename = file.getOriginalFilename();

        // 获取文件后缀名
        String filename_extension = filename.substring(filename
                .lastIndexOf(".") + 1);
        System.out.println("excl的后缀名:" + filename_extension);

        //时间戳做新的文件名，避免中文乱码-重新生成filename
        long filename1 = new Date().getTime();
        filename = Long.toString(filename1) + "." + filename_extension;

        //去掉后缀的文件名
        String filename2 = filename.substring(0, filename.lastIndexOf("."));
        System.out.println("excl名为:" + filename2);

        //源地址+重命名后的名+文件后缀
        String yuanPATH = (path + filename);

        System.out.println("excl的完整文件名1:" + filename);
        System.out.println("源excl路径为:" + yuanPATH);


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
        Workbook workbook = WorkbookFactory.create(new File(yuanPATH));
        System.out.println("sheets" + workbook.getNumberOfSheets());
        Sheet sheet = workbook.getSheetAt(0);
        List<Question> questions = new ArrayList<Question>();
        List<Selection> selections = new ArrayList<Selection>();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {//跳过第一行
            Row row = sheet.getRow(i);//取得第i行数据
            Question question = new Question();
            Selection selection = new Selection();
            String[] str = new String[row.getLastCellNum()];
            for (int j = 0; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);//取得第j列数据
                cell.setCellType(CellType.STRING);
                str[j] = cell.getStringCellValue().trim();
            }
            //question.setQuesId(str[0]);
            question.setQuesContent(str[1]);
            question.setQuesType(str[2]);
            //question.setvCourseId(str[3]);
            //selection.setSelectionId(str[0]);
            selection.setSelectionContent(str[1]);
            selection.setIsRight(Integer.parseInt(str[2]));
            //selection.setQuesId(str[3]);
            questions.add(question);
            selections.add(selection);
        }
        /*for(Question question:questions){
            System.out.println(question.getQuesContent());
        }
        for(Selection selection:selections){
            System.out.println(selection.getIsRight());
        }*/
        for (Question question : questions) {
            questionService.addQuestionfromexcl(question);
        }
        for (Selection selection : selections) {
            selectionService.addSelectionfromexcl(selection);
        }
        return upLoadResult;
    }


}
