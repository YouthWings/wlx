package cn.dubidubi.util;

import cn.dubidubi.model.vx.AccessTokenDO;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AccessTokenUtil {
    private String APPID;
    private String APPSECRET;
    private String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    public String getAPPID() {
        return APPID;
    }

    public void setAPPID(String APPID) {
        this.APPID = APPID;
    }

    public String getAPPSECRET() {
        return APPSECRET;
    }

    public void setAPPSECRET(String APPSECRET) {
        this.APPSECRET = APPSECRET;
    }

    /**
     * 初始化 属性
     */
    public void init(){
        InputStream in = AccessTokenUtil.class.getClassLoader().getResourceAsStream("WeChat.properties");
        Properties p = new Properties();
        try {
            p.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.APPID=p.getProperty("APPID");
        this.APPSECRET=p.getProperty("APPSECRET");
    }

    /**
     * 获取token 并且存到数据库
     * @return
     */
    public AccessTokenDO getAccessToken(){
        init();
        AccessTokenDO accessToken = new AccessTokenDO();
        System.out.println(APPID);
        System.out.println(APPSECRET);
        String url =ACCESS_TOKEN_URL.replace("APPID",APPID).replace("APPSECRET",APPSECRET);
        HttpRequest httpRequest =HttpRequest.get(url);
        httpRequest.trustAllCerts();
        httpRequest.trustAllHosts();
        String json=httpRequest.body();
        System.out.println(json);
        JSONObject access_tokeJson= JSONObject.parseObject(json);
        accessToken.setAccessToken(access_tokeJson.getString("access_token"));
        accessToken.setExpiresIn(Integer.parseInt(access_tokeJson.getString("expires_in")));
        System.out.println(accessToken);
        return  accessToken;
    }

}
