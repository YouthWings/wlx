package cn.dubidubi.dao.vx;

import cn.dubidubi.model.vx.Tweets;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TweetsMapper {
    List<Tweets> getTweets(@Param("type") String type,@Param("state")Integer state,@Param("id")Integer id);
    Integer insertTweets(@Param("tweets") Tweets tweets);
    Integer updateState(@Param("id")Integer id,@Param("state")Integer state);
    Integer deleteTweets(@Param("id")Integer id);
}
