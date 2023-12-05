package com.ruoyi.share.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.share.domain.BonusEventLog;

/**
 * 积分变更记录Service接口
 *
 * @author ruoyi
 * @date 2023-11-14
 */
public interface IBonusEventLogService extends IService<BonusEventLog> {

    /**
     * 查询积分变更记录列表
     *
     * @param bonusEventLog 积分变更记录
     * @return 积分变更记录集合
     */
    public List<BonusEventLog> selectBonusEventLogList(BonusEventLog bonusEventLog);

}
