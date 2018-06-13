package cn.dubidubi.dao.vx;

import cn.dubidubi.model.vx.ShareUrl;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShareMapper {
    //插入Share 链接
    Integer insertShare(@Param("shareUrl") ShareUrl shareUrl);
    //根据id查询状态为state的
    List<ShareUrl> selectShareByState(@Param("state") Integer state,@Param("id") Integer id);
    //设置状态把id为id的状态改为state
    Integer uodateShareState(@Param("state") Integer state,@Param("id") Integer id);
    //根据id删除
    Integer deleteShareByid(Integer id);
}
