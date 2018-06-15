package cn.dubidubi.controller;


import cn.dubidubi.model.vx.ShareUrl;
import cn.dubidubi.service.ShareUrlService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/shareOperation")
public class ShareUrlController {
    @Autowired
    private ShareUrlService shareUrlService;

    /**
     * 添加分享链接
     * @param shareUrl
     * @return
     */
    @RequestMapping("/addShare")
    @ResponseBody
    public String addShare(ShareUrl shareUrl){
        System.out.println(shareUrl);
        if(shareUrlService.addShareUrl(shareUrl)){
            return "添加成功";
        }
        return "添加失败";
    }

    /**
     * 获取share列表
     * @return
     */
    @RequestMapping("/getShareList")
    @ResponseBody
    public List<ShareUrl> getShareList(){
        List<ShareUrl> list =null;
        list =shareUrlService.getShareList();
        return  list;
    }

    /**
     * url状态转化
     * @param jsonStr
     * @return
     */
    @RequestMapping("/updateState")
    @ResponseBody
    public String updateState(@RequestBody String jsonStr){
        System.out.println(jsonStr);
        JSONObject jsonObject =JSONObject.parseObject(jsonStr);
        Integer id =(Integer) jsonObject.get("id");
        Integer state =(Integer) jsonObject.get("state");
        if(shareUrlService.updateShareState(state,id)) {
            return "修改成功";
        }
        return "修改失败";
    }

    /**
     * 根据id删除shareurl
     * @param jsonStr
     * @return
     */
    @RequestMapping("/deleteShare")
    @ResponseBody
    public String deleteShare(@RequestBody String jsonStr){
        System.out.println(jsonStr);
        JSONObject jsonObject =JSONObject.parseObject(jsonStr);
        Integer id =(Integer) jsonObject.get("id");
        if(shareUrlService.deleteShareByid(id)){
            return "删除成功";
        }
        return  "删除失败";
    }

    /**
     * 获取发布的链接
     * @return
     */
    @RequestMapping("/getShareOne")
    @ResponseBody
    public List<ShareUrl> getShareOne(@RequestBody String jsonStr){
        System.out.println(jsonStr);
        JSONObject jsonObject =JSONObject.parseObject(jsonStr);
        Integer state=(Integer) jsonObject.get("state");
        List<ShareUrl> list =null;
        list =shareUrlService.getShareOne(state);
        return  list;
    }
}
