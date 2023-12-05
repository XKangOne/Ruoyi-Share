package com.ruoyi.share.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.share.domain.User;
import com.ruoyi.share.domain.dto.UserAddBonusMsgDTO;

/**
 * 分享Service接口
 *
 * @author ruoyi
 * @date 2023-11-14
 */
public interface IUserService extends IService<User> {

    /**
     * 查询分享列表
     *
     * @param user 分享
     * @return 分享集合
     */
    public List<User> selectUserList(User user);

    public void updateBonus(UserAddBonusMsgDTO userAddBonusMsgDTO);

    public User findById(Long userId);
}
