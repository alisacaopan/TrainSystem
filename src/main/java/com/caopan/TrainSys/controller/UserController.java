package com.caopan.TrainSys.controller;

import com.alibaba.fastjson.JSONObject;
import com.caopan.TrainSys.biz.service.UserService;
import com.caopan.TrainSys.constant.FilePath;
import com.caopan.TrainSys.model.User;
import com.caopan.TrainSys.model.upLoadResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    private String root = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" +
            File.separator + "resources" + File.separator + "static" + File.separator;

    @PostMapping(value = "/insert")
    public Integer insert(@RequestParam("name") String name,
                          @RequestParam ("mobile") String mobile,
                          @RequestParam("openId") String openId,
                          @RequestParam ("idCard")String idCard,
                          @RequestParam ("classId")int classId,
                          @RequestParam("role")String role){
        User user = new User();
        user.setName(name);
        user.setMobile(mobile);
        user.setIdCard(idCard);
        user.setOpenId(openId);
        user.setClassId(classId);
        user.setRole(role);

        int index = 0;
        try {
            if (userService.add(user) == 1) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            return index;
        }
    }

    @PostMapping(value = "/insertByWeChat")
    public Integer insertByWeChat(@RequestParam Long id,
                                  @RequestParam String name,
                                  @RequestParam String mobile,
                                  @RequestParam String idCard,
                                  @RequestParam String openId,
                                  @RequestParam int classId) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setMobile(mobile);
        user.setIdCard(idCard);
        user.setOpenId(openId);
        user.setClassId(classId);
        int index = 0;
        try {
            if (userService.add(user) == 1) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            return index;
        }

    }

    @PostMapping(value = "/update")
    public Integer update(@RequestParam("id") Long id,
                          @RequestParam("name") String name,
                          @RequestParam("mobile") String mobile,
                          @RequestParam("idCard") String idCard,
                          @RequestParam("classId") int classId) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setMobile(mobile);
        user.setIdCard(idCard);
        user.setClassId(classId);
        int index = 0;
        try {
            if (userService.update(user) == 1) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            return index;
        }
    }

    @PostMapping(value = "/updateByWeChat")
    public Integer updateByWeChat(@RequestBody User user) {
        int index = 0;
        try {
            if (userService.update(user) == 1) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            return index;
        }
    }

    @GetMapping(value = "/delete")
    public long delete(@RequestParam(value = "id") long id) {
        int index = 0;
        try {
            if (userService.delete(id) == 1) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            return index;
        }
    }

    @GetMapping(value = "/getUserByIdcard")
    public User getUserByIdcard(@RequestParam("idCard") String idcard) {
        int index = 0;
        try {
            if (userService.getUserByIdcard(idcard) != null) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            if (index == 1) {
                return userService.getUserByIdcard(idcard);
            } else {
                return null;
            }
        }
    }

    @GetMapping(value = "/getUserByMobileAndIdCard")
    public List<User> getUserByMobileAndIdCard(@RequestParam("mobile") String mobile,
                                         @RequestParam("idCard")String idCard) {
        int index = 0;
        try {
            if (userService.getUserByMobileAndIdCard(mobile,idCard) != null) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            if (index == 1) {
                return userService.getUserByMobileAndIdCard(mobile,idCard);
            } else {
                return null;
            }
        }
    }

    @GetMapping(value = "/getUserByMobile")
    public User getUserByMobile(@RequestParam("mobile") String mobile) {
        int index = 0;
        try {
            if (userService.getUserByMobile(mobile) != null) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            if (index == 1) {
                return userService.getUserByMobile(mobile);
            } else {
                return null;
            }
        }
    }

    @GetMapping(value = "/getUserByOpenId")
    public User getUserByOpenId(@RequestParam("openId") String openId) {
        int index = 0;
        try {
            if (userService.getUserByOpenId(openId).getId() > 0) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            if (index == 1) {
                return userService.getUserByOpenId(openId);
            } else {
                return null;
            }
        }
    }

    @GetMapping(value = "/getUserByClassId")
    public List<User> getUserByClassId(@RequestParam("classId") Integer classid) {
        int index = 0;
        try {
            if (userService.getUserByClassId(classid).size() > 0) {
                index = 1;
            } else {
                index = 0;
            }
        } catch (Exception e) {
        } finally {
            if (index == 1) {
                return userService.getUserByClassId(classid);
            } else {
                return null;
            }
        }
    }

    @PostMapping("/addstudent")
    public upLoadResult addstudents(@RequestParam("file-input") MultipartFile file, HttpServletRequest req) throws IOException, InvalidFormatException {

        System.out.println("进入addstudent控制层");
        upLoadResult upLoadResult = new upLoadResult();
        String path = root + FilePath.STUDENT_FOLDER;
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
        //解析excl并向数据库插入数据
        Workbook workbook = WorkbookFactory.create(new File(yuanPATH));
        System.out.println("sheets" + workbook.getNumberOfSheets());
        Sheet sheet = workbook.getSheetAt(0);
        List<User> list = new ArrayList<User>();
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {//跳过第一行
            Row row = sheet.getRow(i);//取得第i行数据
            User user = new User();
            String[] str = new String[row.getLastCellNum()];
            for (int j = 0; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);//取得第j列数据
                cell.setCellType(CellType.STRING);
                str[j] = cell.getStringCellValue().trim();
            }
            //user.setId(2);
            user.setName(str[0]);
            user.setPassword("123456");
            user.setMobile(str[1]);
            user.setIdCard(str[2]);
//            user.setOpenId(str[4]);
            user.setRole("1");
            user.setClassId(Integer.parseInt(str[3]));
            list.add(user);
        }
        for (User user : list) {
            String mobile = user.getMobile();
            Matcher m = p.matcher(mobile);
            if (userService.getUserByIdcard(user.getIdCard()) != null) {
                System.out.println(user.getName() + "的身份证号码已经存在");
            } else if (!m.matches()) {
                System.out.println(user.getName() + "的手机号码格式错误");
            } else {
                userService.insertStudentFromexcl(user);
            }
        }
        return upLoadResult;
    }


    @ResponseBody
    @RequestMapping(value = "pageInfo", produces = "html/text;charset=UTF-8")
    public String pageInfo(@RequestParam int pageNumber, int pageSize, HttpServletResponse response) {
        response.setContentType("text/json");
        System.out.println(pageNumber + "====" + pageSize);
        response.setCharacterEncoding("utf-8");

        //userList查询要放到startPage下面
        PageHelper.startPage(pageNumber, pageSize);
        List<User> userList = userService.findAllStudents();
        PageInfo<User> page = new PageInfo<>(userList);
        //取出查询结果
        List<User> rows = page.getList();
        int total = (int) page.getTotal();
        //转换为json数据
        JSONObject result = new JSONObject();
        result.put("rows", rows);
        result.put("total", total);
        System.out.println(result.toJSONString());
        return result.toJSONString();
    }


    @PostMapping("/managerLogin")
    public String loginUser(@RequestParam("mobile") String mobile,
                            @RequestParam("idCard") String idCard,
                            @RequestParam("password") String password) {


        User user = userService.getUserByMobile(mobile);
        if (user == null) {
            return "手机号输入错误";
        } else {
            if (!user.getIdCard().equals(idCard)) {
                return "身份证输入错误";
            } else {
                if (!user.getPassword().equals(password)) {
                    return "密码输入错误";
                }
            }
        }

        return "1";
    }


    @GetMapping("/getAllStudents")
    public List<User> getAllStudents() {
        return userService.findAllStudents();
    }
}
