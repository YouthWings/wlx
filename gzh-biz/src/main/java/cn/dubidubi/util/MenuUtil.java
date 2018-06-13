package cn.dubidubi.util;

import cn.dubidubi.model.vx.Button;
import cn.dubidubi.model.vx.ComButton;
import cn.dubidubi.model.vx.ViewButton;
import cn.dubidubi.model.vx.Menu;
import com.alibaba.fastjson.JSONObject;

public class MenuUtil {
    public Menu getMenu(){
        ViewButton button11 =new ViewButton();
        button11.setName("宁波大学");
        button11.setType("view");
        button11.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxed033cf295ee6dd8&redirect_uri=http://imlzz.free.ngrok.cc/WeChat/operation/getVxUser.do&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");

        ViewButton button12 =new ViewButton();
        button12.setName("浙江大学宁波理工学院");
        button12.setType("view");
        button12.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxed033cf295ee6dd8&redirect_uri=http://imlzz.free.ngrok.cc/WeChat/operation/getVxUser.do&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");

        ViewButton button13 =new ViewButton();
        button13.setName("浙江万里学院");
        button13.setType("view");
        button13.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxed033cf295ee6dd8&redirect_uri=http://imlzz.free.ngrok.cc/WeChat/operation/getVxUser.do&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");

        ComButton mainButton1= new ComButton();
        mainButton1.setName("请选择校区");
        mainButton1.setSub_button(new Button[]{button11,button12,button13});

        ViewButton button21 =new ViewButton();
        button21.setName("百度一下");
        button21.setType("view");
        button21.setUrl("http://www.baidu.com");

        ComButton mainButton2= new ComButton();
        mainButton2.setName("敬请期待");
        mainButton2.setSub_button(new Button[]{button21});

        ViewButton button31 =new ViewButton();
        button31.setName("百度一下");
        button31.setType("view");
        button31.setUrl("http://www.baidu.com");

        ComButton mainButton3= new ComButton();
        mainButton3.setName("敬请期待");
        mainButton3.setSub_button(new Button[]{button31});

        Menu menu = new Menu();
        menu.setButton(new Button[]{mainButton1,mainButton2,mainButton3});
        return  menu;
    }

    public static void main(String[] args) {
        MenuUtil menuUtil = new MenuUtil();
        System.out.println(JSONObject.toJSONString(menuUtil.getMenu()));
    }
}
