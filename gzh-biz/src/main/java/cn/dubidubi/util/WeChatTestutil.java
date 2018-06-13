package cn.dubidubi.util;

import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;

public class WeChatTestutil {
    private String creat_url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    public void creat(){
        MenuUtil menuUtil = new MenuUtil();
        String test = JSONObject.toJSONString(menuUtil.getMenu());

        AccessTokenUtil accessTokenUtil = new AccessTokenUtil();
        String url =creat_url.replace("ACCESS_TOKEN",accessTokenUtil.getAccessToken().getAccessToken());
        System.out.println(url);
        HttpRequest httpRequest=HttpRequest.post(url);
        httpRequest.trustAllCerts();
        httpRequest.trustAllHosts();
        httpRequest.send(test);
        String json =httpRequest.body();
        System.out.println(json);
    }
    //调自定义接口
    public static void main(String[] args) {
        WeChatTestutil weChatTestutil= new WeChatTestutil();
        weChatTestutil.creat();
    }
}
