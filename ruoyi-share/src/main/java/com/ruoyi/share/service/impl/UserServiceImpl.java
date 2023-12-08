package com.ruoyi.share.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.share.domain.BonusEventLog;
import com.ruoyi.share.domain.dto.UserAddBonusMsgDTO;
import com.ruoyi.share.mapper.BonusEventLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.common.utils.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.ruoyi.share.mapper.UserMapper;
import com.ruoyi.share.domain.User;
import com.ruoyi.share.service.IUserService;

import javax.annotation.Resource;

/**
 * 分享Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-14
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private BonusEventLogMapper bonusEventLogMapper;
    @Resource
    private ShareServiceImpl shareService;

    /**
     * 查询分享列表
     *
     * @param user 分享
     * @return 分享
     */
    @Override
    public List<User> selectUserList(User user)
    {
        List<User> userList = userMapper.selectList(buildQueryWrapper(user));
        return userList;
    }


    private LambdaQueryWrapper<User> buildQueryWrapper(User query) {
        Map<String, Object> params = query.getParams();
        LambdaQueryWrapper<User> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(query.getPhone()), User::getPhone, query.getPhone());
        lqw.eq(StringUtils.isNotBlank(query.getPassword()), User::getPassword, query.getPassword());
        lqw.like(StringUtils.isNotBlank(query.getNickname()), User::getNickname, query.getNickname());
        lqw.eq(StringUtils.isNotBlank(query.getRoles()), User::getRoles, query.getRoles());
        lqw.eq(StringUtils.isNotBlank(query.getAvatarUrl()), User::getAvatarUrl, query.getAvatarUrl());
        lqw.eq(query.getBonus() != null, User::getBonus, query.getBonus());
        lqw.orderByDesc(User::getCreateTime);
        return lqw;
    }

    public void updateBonus(UserAddBonusMsgDTO userAddBonusMsgDTO) {
        System.out.println(userAddBonusMsgDTO);
        //1.为用户修改积分
        Long userId = userAddBonusMsgDTO.getUserId();
        Integer bonus = userAddBonusMsgDTO.getBonus();
        User user = userMapper.selectById(userId);
        user.setBonus(user.getBonus() + bonus);
        userMapper.update(user, new QueryWrapper<User>().lambda().eq(User::getId, userId));

        //2.记录日志到 bonus_event_log 表里
        bonusEventLogMapper.insert(
                BonusEventLog.builder()
                        .userId(userId)
                        .value(bonus)
                        .description(userAddBonusMsgDTO.getDescription())
                        .event(userAddBonusMsgDTO.getEvent())
                        .createTime(new Date())
                        .build()
        );
        log.info("积分添加完毕...");
    }
    public User findById(Long userId) {
        return userMapper.selectById(userId);
    }

}
