package cn.dubidubi.service.impl;

import cn.dubidubi.model.vx.VxUser;
import cn.dubidubi.service.GetVxUserService;
import cn.dubidubi.util.AccessTokenUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import com.sun.jnlp.ApiDialog;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.Map;
@Service
public class GetVxUserServiceImpl implements GetVxUserService {
    //通过这个获取 自己存储好的appid 以及APPSECRET
    private AccessTokenUtil accessTokenUtil;
    //获取授权认证的相关信息 openid scope 等
    private String GET_WEBAUTH_URL="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    //通过accesstoken 和 openid 获取用户信息
    private String GET_WEIXIN_USER_URL="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    //验证授权access_token是否有效
    private  String  VALIDATION_ACCESSTOKEN_URL="https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";
    //刷新access_token
    private String REFRESH_ACCESSTOKEN_URL="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
    /**
     *
     */
    public void init(){
        this.accessTokenUtil = new AccessTokenUtil();
        accessTokenUtil.init();
    }

    /**
     * 获取微信用户
     * @param code
     * @return
     */
    public VxUser getVxUser(String code) {
        Map<String,String> map= oauth2GetOpenid(code);
        String oauth2Access_Token=map.get("AccessToken");
        String openid=map.get("Openid");
        //验证accesstoken是否有效
        if(!AuthorizationJudgment(oauth2Access_Token,openid)){
            oauth2Access_Token=refreshToen(map.get("refresh_token"),accessTokenUtil.getAPPID());
        }
        VxUser vxUser =getVxUserByaccessTokenAndOpenid(oauth2Access_Token,openid);
        return vxUser;
    }

    /**
     * 通过 accesstoken 和openid 获取用户信息
     * @param accesstoken
     * @param openid
     * @return
     */
    private VxUser getVxUserByaccessTokenAndOpenid(String accesstoken,String openid){
        VxUser vxUser = null;
        String url =GET_WEIXIN_USER_URL.replace("ACCESS_TOKEN",accesstoken).replace("OPENID",openid);
        //请求 并且接收返回数据
        HttpRequest httpRequest=HttpRequest.get(url);
        httpRequest.trustAllCerts();
        httpRequest.trustAllHosts();
        String jsonStr=httpRequest.body();
        //System.out.println("获取用户信息"+jsonStr);
        JSONObject jsonObject =JSONObject.parseObject(jsonStr);
        if(jsonObject!=null){
            vxUser = new VxUser();
            // 用户的标识
            vxUser.setOpenId(jsonObject.getString("openid"));
            // 昵称
            vxUser.setNickname(jsonObject.getString("nickname"));
            // 用户的性别（1是男性，2是女性，0是未知）
            vxUser.setSex(Integer.parseInt(jsonObject.getString("sex")));
            // 用户所在国家
            vxUser.setCountry(jsonObject.getString("country"));
            // 用户所在省份
            vxUser.setProvince(jsonObject.getString("province"));
            // 用户所在城市
            vxUser.setCity(jsonObject.getString("city"));
            // 用户头像
            vxUser.setHeadImgUrl(jsonObject.getString("headimgurl"));
            //用户使用语言
            vxUser.setLanguage(jsonObject.getString("language"));
        }
        return vxUser;
    }

    /**
     * 通过code 获取 授权信息
     * @param code
     * @return
     */
    private Map<String,String> oauth2GetOpenid(String code){
        init();
        String url=GET_WEBAUTH_URL.replace("APPID",accessTokenUtil.getAPPID()).replace("SECRET",accessTokenUtil.getAPPSECRET()).replace("CODE",code);
        HttpRequest httpRequest =HttpRequest.get(url);
        httpRequest.trustAllHosts();
        httpRequest.trustAllCerts();
        String jsonStr =httpRequest.body();
        //System.out.println(jsonStr);
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        //存储返回的数据
        String Openid = String.valueOf(jsonObject.get("openid"));
        String AccessToken = String.valueOf(jsonObject.get("access_token"));
        //用户保存的作用域
        String Scope = String.valueOf(jsonObject.get("scope"));
        String refresh_token = String.valueOf(jsonObject.get("refresh_token"));
        Map<String,String> result = new HashMap<>();
        result.put("Openid",Openid);
        result.put("AccessToken",AccessToken);
        result.put("scope",Scope);
        result.put("refresh_token",refresh_token);
        return result;
    }

    /**
     * 判断授权是否过期
     * @param access_token
     * @return
     */
    private boolean AuthorizationJudgment(String access_token,String openid){
        String url =VALIDATION_ACCESSTOKEN_URL.replace("ACCESS_TOKEN",access_token).replace("OPENID",openid);
        HttpRequest httpRequest =HttpRequest.get(url);
        httpRequest.trustAllHosts();
        httpRequest.trustAllCerts();
        String jsonStr = httpRequest.body();
       // System.out.println("验证："+jsonStr);
        JSONObject message = JSONObject.parseObject(jsonStr);
        if("ok".equals(String.valueOf(message.get("errmsg")))){
            return true;
        }
        return false;
    }

    /**
     * 根据refreshtoken去刷新access_token
     * @param refreshToken
     * @return
     */
    private String refreshToen(String refreshToken,String appid){
        String url =REFRESH_ACCESSTOKEN_URL.replace("APPID",appid).replace("REFRESH_TOKEN",refreshToken);
        HttpRequest httpRequest =HttpRequest.get(url);
        httpRequest.trustAllCerts();
        httpRequest.trustAllHosts();
        String jsonStr =httpRequest.body();
       // System.out.println("刷新后的："+jsonStr);
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        String token=String.valueOf(jsonObject.get("access_token"));
        return token;
    }
}
