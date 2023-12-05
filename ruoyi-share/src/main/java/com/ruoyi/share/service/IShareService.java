package com.ruoyi.share.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.share.domain.Share;

/**
 * 分享Service接口
 *
 * @author ruoyi
 * @date 2023-11-14
 */
public interface IShareService extends IService<Share> {

    /**
     * 查询分享列表
     *
     * @param share 分享
     * @return 分享集合
     */
    public List<Share> selectShareList(Share share);

}
