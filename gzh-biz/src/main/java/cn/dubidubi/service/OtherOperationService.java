package cn.dubidubi.service;

import cn.dubidubi.model.vx.ShareUrl;
import cn.dubidubi.model.vx.Tweets;

import java.util.List;

public interface OtherOperationService {
     //根据相应条件筛选推文
     List<Tweets> getTweets(String type,Integer state,Integer id);
     //设置推文
     boolean setTweets(Tweets tweets);
     //根据id改变推文为state状态
     boolean updateUrlState(Integer id,Integer state);
     //根据id删除推文
     boolean deleteTweetsById(Integer id);
}
