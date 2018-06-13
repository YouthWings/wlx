package cn.dubidubi.service;

import cn.dubidubi.model.vx.ShareUrl;

import java.util.List;

public interface ShareUrlService {
     boolean addShareUrl(ShareUrl shareUrl);

     List<ShareUrl> getShareList();

     boolean updateShareState(Integer state,Integer id);

     boolean deleteShareByid(Integer id);

     List<ShareUrl> getShareOne(Integer state);
}
