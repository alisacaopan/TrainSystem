package com.caopan.TrainSys.utils;

/**
 * @Description 需要的几个参数
 * @time 2018年7月30日 下午2:57:57
 * @author 往事_小哥哥
 */
public class CodeAndOpenId {
	/**
     * 微信开发平台应用ID
     */
    public static final String APP_ID="wxdce09440f3c6b2d3";
    /**
     * 应用对应的凭证
     */
    public static final String APP_SECRET="7b9a5ddd60d2d6859be1641fe36cffdb";
    /**
     * 通过REQUEST_URL获取OPENID
     */
    public static String REQUEST_URL="https://api.weixin.qq.com/sns/jscode2session";
    /**
     * 常量固定值
     */
    public static final String GRANT_TYPE="authorization_code";
}
