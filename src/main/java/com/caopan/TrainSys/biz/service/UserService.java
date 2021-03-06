package com.caopan.TrainSys.biz.service;

import com.alibaba.fastjson.JSONObject;
import com.caopan.TrainSys.biz.dao.SignInDao;
import com.caopan.TrainSys.constant.AuthResponseCode;
import com.caopan.TrainSys.biz.dao.UserDao;
import com.caopan.TrainSys.model.LoginResult;
import com.caopan.TrainSys.model.SignIn;
import com.caopan.TrainSys.model.User;
import com.caopan.TrainSys.utils.CodeAndOpenId;
import com.caopan.TrainSys.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private SignInDao signInDao;

//    @Autowired
//    private TokenService tokenService;

    public LoginResult login(String openid) {

        User user = userDao.getUserByOpenId(openid);
        LoginResult loginResult = new LoginResult();
        if (user == null) {
            loginResult.setCode(AuthResponseCode.USER_NOT_AUTH);
            loginResult.setDesc(AuthResponseCode.USER_NOT_AUTH_DESC);
            loginResult.setOpenId(openid);
            return loginResult;
        }
        long userId = user.getId();
        String username = user.getName();
        String code = user.getRole();
        loginResult.setUserId(userId);
        loginResult.setUsername(username);
        loginResult.setCode(code);
        loginResult.setOpenId(openid);
        return loginResult;
    }


    public String getOpenId(String code) {
        String appid = CodeAndOpenId.APP_ID;
        String appsecret = CodeAndOpenId.APP_SECRET;
        String grant_type = CodeAndOpenId.GRANT_TYPE;
        String requestUrl = CodeAndOpenId.REQUEST_URL;
        String params = "appid=" + appid + "&secret=" + appsecret + "&js_code=" + code + "&grant_type=" + grant_type;
        String data = HttpUtil.get(requestUrl, params);
        System.out.println(data);
        JSONObject json = JSONObject.parseObject(data);
        String openId = String.valueOf(json.get("openid"));
        return openId;
    }

    public Integer updateOpenId(String openId, String mobile) {
        return userDao.updateOpenId(openId, mobile);
    }

    public User getUserByIdcard(String idCard) {
        return userDao.getUserByIdcard(idCard);
    }

    public User getUserByMobile(String mobile) {
        return userDao.getUserByMobile(mobile);
    }


    public List<User> getUserByClassId(Integer idcard) {
        return userDao.getUserByClassId(idcard);
    }

    public User getUserByOpenId(String openid){
        return  userDao.getUserByOpenId(openid);
    }
    public int add(User user) {
        return userDao.add(user);
    }

    public int update(User user) {
        return userDao.update(user);
    }

    public int delete(long id) {
      //  删除学生需要删除签到表
        signInDao.deleteByUserId(id);
        return userDao.delete(id);
    }

    public void insertStudentFromexcl(User user){
        insert(user);
    }

    public List<User> findAllStudents(){
        return userDao.findAllStudents();
    }

    public List<User> getUserByMobileAndIdCard(String mobile,String idCard){return userDao.getUserByMobileAndIdCard(mobile,idCard);}

    public User getUserById(long id){
        return userDao.getUserById(id);
    }


    public int insert(User user) {
        userDao.add(user);
        SignIn signIn=new SignIn();
        signIn.setUserId(user.getId());
        signIn.setSignStatus("0");
        return signInDao.insert(signIn);
    }

}
