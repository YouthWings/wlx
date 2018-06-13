package cn.dubidubi.service.impl;

import cn.dubidubi.dao.vx.ShareMapper;
import cn.dubidubi.model.vx.ShareUrl;
import cn.dubidubi.service.ShareUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareUrlServiceImpl implements ShareUrlService{
    @Autowired
    ShareMapper shareMapper;
    /**
     * 添加分享链接对象
     * @param shareUrl
     * @return
     */
    public boolean addShareUrl(ShareUrl shareUrl) {
        List<ShareUrl> list =null;
        list=shareMapper.selectShareByState(1,null);
        System.out.println("查询结果："+list);
        //分享链接只允许一条链接在开放状态
        if("[]".equals(list.toString())){
            shareUrl.setState(1);
        }else {
            shareUrl.setState(0);
        }
        System.out.println("设置状态后"+shareUrl);
        Integer num =shareMapper.insertShare(shareUrl);
        if(num>0){
            return true;
        }
        return false;
    }

    /**
     * 获取列表
     * @return
     */
    public List<ShareUrl> getShareList() {
        List<ShareUrl> list =null;
        list=shareMapper.selectShareByState(null,null);
        return list;
    }

    /**
     *
     * @param state
     * @param id
     * @return
     */
    public boolean updateShareState(Integer state, Integer id) {
        if(state==1){
         //状态由0改为1，把其他状态为1的改为0
            List<ShareUrl> list =null;
            list=shareMapper.selectShareByState(1,null);
            for(int i=0;i<list.size();i++){
                shareMapper.uodateShareState(0,((ShareUrl)list.get(i)).getId());
            }
        }
        Integer num=shareMapper.uodateShareState(state,id);
        if(num>0){
            return true;
        }
        return false;
    }

    public boolean deleteShareByid(Integer id){
        Integer num =shareMapper.deleteShareByid(id);
        if(num>0){
            return true;
        }
        return false;
    }

    /**
     * 回去发布的链接信息
     * @param state
     * @return
     */
    @Override
    public List<ShareUrl> getShareOne(Integer state) {
        List<ShareUrl> list =null;
        list=shareMapper.selectShareByState(state,null);
        return list;
    }
}
