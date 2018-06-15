package cn.dubidubi.controller;

import cn.dubidubi.base.redis.PageView;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pageJump")
public class PageJumpController {
    @Autowired
    PageView pageView;
    /**
     * 跳转到主界面
     * @return
     */
    @RequestMapping("/main")
    public String test() {
        pageView.incr("WeChat");
        return "/html/main.html";
    }

    /**
     * 跳转到拼车页面
     * @return
     */
    @RequestMapping("/carpooling")
    public String carPooling(){
        pageView.incr("carpooling");
        return  "/html/carpooling.html";
    }

    /**
     * 跳转到旅游页面
     * @return
     */
    @RequestMapping("/tourism")
    public String tourism(){
        pageView.incr("tourism");
        return  "tourism";
    }


}
