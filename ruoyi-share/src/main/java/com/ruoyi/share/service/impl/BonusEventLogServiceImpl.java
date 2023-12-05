package com.ruoyi.share.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.common.utils.StringUtils;
import java.util.List;
import java.util.Map;
import com.ruoyi.share.mapper.BonusEventLogMapper;
import com.ruoyi.share.domain.BonusEventLog;
import com.ruoyi.share.service.IBonusEventLogService;

import javax.annotation.Resource;

/**
 * 积分变更记录Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-14
 */
@Service
public class BonusEventLogServiceImpl extends ServiceImpl<BonusEventLogMapper, BonusEventLog> implements IBonusEventLogService {

    @Resource
    private BonusEventLogMapper bonusEventLogMapper;

    /**
     * 查询积分变更记录列表
     *
     * @param bonusEventLog 积分变更记录
     * @return 积分变更记录
     */
    @Override
    public List<BonusEventLog> selectBonusEventLogList(BonusEventLog bonusEventLog)
    {
        List<BonusEventLog> bonusEventLogList = bonusEventLogMapper.selectList(buildQueryWrapper(bonusEventLog));
        return bonusEventLogList;
    }


    private LambdaQueryWrapper<BonusEventLog> buildQueryWrapper(BonusEventLog query) {
//        Map<String, Object> params = query.getParams();
        LambdaQueryWrapper<BonusEventLog> lqw = Wrappers.lambdaQuery();
        lqw.eq(query.getUserId() != null, BonusEventLog::getUserId, query.getUserId());
        lqw.eq(query.getValue() != null, BonusEventLog::getValue, query.getValue());
        lqw.eq(StringUtils.isNotBlank(query.getEvent()), BonusEventLog::getEvent, query.getEvent());
        lqw.orderByDesc(BonusEventLog::getCreateTime);
        lqw.eq(StringUtils.isNotBlank(query.getDescription()), BonusEventLog::getDescription, query.getDescription());
        return lqw;
    }

}
