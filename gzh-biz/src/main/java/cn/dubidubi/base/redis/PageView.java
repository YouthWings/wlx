package cn.dubidubi.base.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PageView {
    @Autowired
    JedisPool jedisPool;


    /**
     * 每次访问页面访问量加一
     * @param key
     */
    public void incr(String key){
        Jedis resource = jedisPool.getResource();
        String name =null;
        name= resource.get(key);
        if(name==null){
            resource.set(key,"1");
            if(!key.endsWith("All")){
                int s =(int)(24*3600-System.currentTimeMillis()/1000);
                resource.expire(key,s);
            }
        }else{
            resource.incr(key);
        }
        resource.close();
    }

    /**
     * 获取所有页面的访问量
     * @return
     */
    public Map<String,String> getAllPageView(String str[]){
        Map<String,String> pageViewMap=new HashMap<>();
        for(int i=0;i<str.length;i++){
            String value = getOnePageView(str[i]);
            pageViewMap.put(str[i],value);
        }
        return  pageViewMap;
    }
    private String getOnePageView(String key){
        Jedis resource = jedisPool.getResource();
        String value =null;
        value= resource.get(key);
        resource.close();
        if(value==null){
            return "0";
        }else{
            return value;
        }

    }
}
