package com.ruoyi.share.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.common.utils.StringUtils;
import java.util.List;
import java.util.Map;
import com.ruoyi.share.mapper.MidUserShareMapper;
import com.ruoyi.share.domain.MidUserShare;
import com.ruoyi.share.service.IMidUserShareService;

/**
 * 用户-分享中间【描述用户购买的分享】Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-14
 */
@Service
public class MidUserShareServiceImpl extends ServiceImpl<MidUserShareMapper, MidUserShare> implements IMidUserShareService {

    @Autowired
    private MidUserShareMapper midUserShareMapper;

    /**
     * 查询用户-分享中间【描述用户购买的分享】列表
     *
     * @param midUserShare 用户-分享中间【描述用户购买的分享】
     * @return 用户-分享中间【描述用户购买的分享】
     */
    @Override
    public List<MidUserShare> selectMidUserShareList(MidUserShare midUserShare)
    {
        List<MidUserShare> midUserShareList = midUserShareMapper.selectList(buildQueryWrapper(midUserShare));
        return midUserShareList;
    }


    private LambdaQueryWrapper<MidUserShare> buildQueryWrapper(MidUserShare query) {
        Map<String, Object> params = query.getParams();
        LambdaQueryWrapper<MidUserShare> lqw = Wrappers.lambdaQuery();
        lqw.orderByDesc(MidUserShare::getCreateTime);
        lqw.eq(query.getShareId() != null, MidUserShare::getShareId, query.getShareId());
        lqw.eq(query.getUserId() != null, MidUserShare::getUserId, query.getUserId());
        return lqw;
    }

}
