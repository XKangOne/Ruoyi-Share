package com.ruoyi.share.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.share.domain.MidUserShare;

/**
 * 用户-分享中间【描述用户购买的分享】Service接口
 *
 * @author ruoyi
 * @date 2023-11-14
 */
public interface IMidUserShareService extends IService<MidUserShare> {

    /**
     * 查询用户-分享中间【描述用户购买的分享】列表
     *
     * @param midUserShare 用户-分享中间【描述用户购买的分享】
     * @return 用户-分享中间【描述用户购买的分享】集合
     */
    public List<MidUserShare> selectMidUserShareList(MidUserShare midUserShare);

}
