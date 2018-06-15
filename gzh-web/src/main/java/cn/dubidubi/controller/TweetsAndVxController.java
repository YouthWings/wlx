package cn.dubidubi.controller;

import cn.dubidubi.base.redis.PageView;
import cn.dubidubi.model.vx.Tweets;
import cn.dubidubi.model.vx.VxUser;
import cn.dubidubi.service.GetVxUserService;
import cn.dubidubi.service.OtherOperationService;
import com.alibaba.fastjson.JSONObject;
import freemarker.ext.beans.HashAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.lang.model.element.TypeElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/operation")
public class TweetsAndVxController {
    @Autowired
    private GetVxUserService getVxUserService;
    @Autowired
    private OtherOperationService otherOperationService;
    @Autowired
    private PageView pageView;
    @Resource(name = "multipartResolver")
    private CommonsMultipartResolver multipartResolver;
    /**
     * 获取授权用户对象信息
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("/getVxUser")
    public String getVxUser(HttpServletRequest request, HttpSession session){
        String code = request.getParameter("code");
        VxUser vxUser = getVxUserService.getVxUser(code);
        session.setAttribute("vxUser",vxUser);
        return "redirect:/pageJump/main.do";
    }

    /**
     * 获取推文链接
     * @return
     */
    @RequestMapping("getTweets")
    @ResponseBody
    public List<Tweets> getUrl(@RequestBody String type){
        JSONObject jsonObject =JSONObject.parseObject(type);
        String value =(String) jsonObject.get("type");
        Integer state =(Integer) jsonObject.get("state");
        List<Tweets> tweets=null;
        if("0".equals(value)){
            value=null;
        }
        if(state==2){
            state=null;
        }
        System.out.println(value+","+state);
        tweets=otherOperationService.getTweets(value,state,null);
        System.out.println(tweets);
        return tweets;
    }

    /**
     * 设置推文链接
     * @param tweets
     * @return
     */
    @RequestMapping("/setTweets")
    @ResponseBody
    public String setTweetsUrl(Tweets tweets,HttpServletRequest request){
        //保存图片
        //存放路径
        String path = null;
        //设置图片访问名
        String filename=null;
        multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    try {
                        //文件名
                        String fakeName = getStr(tweets.getUrltype())+Math.random()* 10000;
                        //后缀名
                        String ext = "";
                        int index = file.getOriginalFilename().lastIndexOf(".");
                        //有无后缀名
                        if (index > -1) {
                            ext = file.getOriginalFilename().substring(index + 1);
                            path = "D:/gitspace/wlx/gzh-web/src/main/webapp/image/tweetsurl/" + fakeName + "." + ext;
                           filename=fakeName+"."+ext;
                        } else {
                            path = "D:/gitspace/wlx/gzh-web/src/main/webapp/image/tweetsurl/" + fakeName;
                        }
                        file.transferTo(new File(path));
                    } catch (IOException e) {
                        e.printStackTrace();
                        return "failure";
                    }
                }
            }
        }
        tweets.setImg("/WeChat/image/tweetsurl/"+filename);
        if(otherOperationService.setTweets(tweets)){
            return "插入成功";
        }
        return "插入失败";
    }
    /**
     * 获取访问量
     * @return
     */
    @RequestMapping("getViewNum")
    @ResponseBody
    public String getViewNum(){
        String str[]={"supermarket","tourism","TextualResearch","carpooling","party","schedule","CheckResult","RunningErrands","bazaar","WeChat"};
        String str1[]={"supermarketAll","tourismAll","TextualResearchAll","carpoolingAll","partyAll","scheduleAll","CheckResultAll","RunningErrandsAll","bazaarAll","WeChatAll"};

        Map<String,String> map = pageView.getAllPageView(str);
        Map<String,String> map1 = pageView.getAllPageView(str1);

        return JSONObject.toJSONString(map)+"|"+JSONObject.toJSONString(map1);
    }

    /**
     * 设置访问量
     * @param key
     * @return
     */
    @RequestMapping("/setViewNum")
    @ResponseBody
    public String setViewNum(@RequestBody String key){
        JSONObject jsonObject =JSONObject.parseObject(key);
        System.out.println(key);
        pageView.incr((String) jsonObject.get("key"));
        pageView.incr(jsonObject.get("key")+"All");
        return "成功";
    }

    /**
     * 设置推文的状态
     * @param jsonStr
     * @return
     */
    @RequestMapping("/setState")
    @ResponseBody
    public String setState(@RequestBody String jsonStr){
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        Integer id=(Integer) jsonObject.get("id");
        Integer state=(Integer) jsonObject.get("state");
        if(otherOperationService.updateUrlState(id,state)){
            return "修改成功";
        }
        return "修改失败";
    }

    /**
     * 根据id删除
     * @param jsonStr
     * @return
     */
    @RequestMapping(value = "deleteTweets",produces = "application/json;charset=utf-8")//解决中文乱码问题
    @ResponseBody
    public String deleteTweets(@RequestBody String jsonStr){
        JSONObject jsonObject =JSONObject.parseObject(jsonStr);
        Integer id =(Integer)jsonObject.get("id");
        if(otherOperationService.deleteTweetsById(id)){
            return "删除成功";
        }
        return "删除失败";
    }

    /**
     * 根据tweets相应的类型返回相应名称的字符串
     */
    private String getStr(String type){
        if("1".equals(type)){
           return "supermarket";
        }else if("2".equals(type)){
            return "tourism";
        }else if("3".equals(type)){
            return "TextualResearch";
        }else if("4".equals(type)){
            return "party";
        }else if("5".equals(type)){
            return "RunningErrands";
        }else if("6".equals(type)){
            return "bazaar";
        }
        return "没有对应的字符串";
    }
}
