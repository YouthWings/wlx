package cn.dubidubi.service.impl;

import cn.dubidubi.dao.vx.ShareMapper;
import cn.dubidubi.dao.vx.TweetsMapper;
import cn.dubidubi.model.vx.ShareUrl;
import cn.dubidubi.model.vx.Tweets;
import cn.dubidubi.service.OtherOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.plugin2.os.windows.FLASHWINFO;

import java.io.File;
import java.util.List;
@Service
public class OtherOperationServiceImpl implements OtherOperationService {
    @Autowired
    TweetsMapper tweetsMapper;


    /**
     *
     * @param type
     *          推文类型
     * @param state
     *          推文状态
     * @param id
     *          推文id
     * @return
     */
    public List<Tweets> getTweets(String type,Integer state,Integer id) {
        List<Tweets> list =null;
        list=tweetsMapper.getTweets(type,state,id);
        if(list==null){
            Tweets tweets =new Tweets();
            tweets.setTitle("获取失败");
            list.add(tweets);
        }
        System.out.println("list"+list);
        return list;
    }

    /**
     * 设置相关推文的链接
     * @param tweets
     *          推文对象
     * @return
     */
    public boolean setTweets(Tweets tweets){
        System.out.println("初始"+tweets);
        tweets.setState(1);
        Integer num=tweetsMapper.insertTweets(tweets);
        System.out.println("num:" +num);
        if(num>0){
            return true;
        }
        return  false;
    }

    /**
     * 设置url状态
     * @param id
     * @param state
     * @return
     */
    public boolean updateUrlState(Integer id, Integer state) {
        Integer num=tweetsMapper.updateState(id,state);
        if(num>0){
            return true;
        }
        return false;
    }

    /**
     * 根据id删除推文信息
     * @param id
     *         主键id
     * @return bool
     */
    public boolean deleteTweetsById(Integer id){
        //删除图片
        List<Tweets> list =null;
        list=tweetsMapper.getTweets(null,null,id);
        String str =((Tweets)list.get(0)).getImg();
        String newstr=str.replace("/WeChat","D:/gitspace/wlx/gzh-web/src/main/webapp");
        deleteImg(newstr);
        //删除数据库记录
        Integer num =tweetsMapper.deleteTweets(id);
        System.out.println("删除条数："+num);
        if(num>0){
            return true;
        }
        return  false;
    }



    /**
     * 删除图片
     * @param filename
     */
    private void deleteImg(String filename){
        File file = new File(filename);
        if(file.exists()&&file.isFile()){
            file.delete();

        }
    }
}
