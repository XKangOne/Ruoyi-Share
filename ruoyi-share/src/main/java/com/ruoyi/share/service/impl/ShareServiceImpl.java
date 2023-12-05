package com.ruoyi.share.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.resp.CommonResp;
import com.ruoyi.share.domain.MidUserShare;
import com.ruoyi.share.domain.User;
import com.ruoyi.share.domain.dto.ExchangeDTO;
import com.ruoyi.share.mapper.MidUserShareMapper;
import com.ruoyi.share.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.common.utils.StringUtils;
import java.util.List;
import java.util.Map;
import com.ruoyi.share.mapper.ShareMapper;
import com.ruoyi.share.domain.Share;
import com.ruoyi.share.service.IShareService;

import javax.annotation.Resource;

/**
 * 分享Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-14
 */
@Service
public class ShareServiceImpl extends ServiceImpl<ShareMapper, Share> implements IShareService {

    @Resource
    private ShareMapper shareMapper;



    /**
     * 查询分享列表
     *
     * @param share 分享
     * @return 分享
     */
    @Override
    public List<Share> selectShareList(Share share)
    {
        List<Share> shareList = shareMapper.selectList(buildQueryWrapper(share));
        return shareList;
    }


    private LambdaQueryWrapper<Share> buildQueryWrapper(Share query) {
        Map<String, Object> params = query.getParams();
        LambdaQueryWrapper<Share> lqw = Wrappers.lambdaQuery();
        lqw.eq(query.getUserId() != null, Share::getUserId, query.getUserId());
        lqw.eq(StringUtils.isNotBlank(query.getTitle()), Share::getTitle, query.getTitle());
        lqw.orderByDesc(Share::getCreateTime);
        lqw.eq(query.getIsOriginal() != null, Share::getIsOriginal, query.getIsOriginal());
        lqw.eq(StringUtils.isNotBlank(query.getAuthor()), Share::getAuthor, query.getAuthor());
        lqw.eq(StringUtils.isNotBlank(query.getCover()), Share::getCover, query.getCover());
        lqw.eq(StringUtils.isNotBlank(query.getSummary()), Share::getSummary, query.getSummary());
        lqw.eq(query.getPrice() != null, Share::getPrice, query.getPrice());
        lqw.eq(StringUtils.isNotBlank(query.getDownloadUrl()), Share::getDownloadUrl, query.getDownloadUrl());
        lqw.eq(query.getBuyCount() != null, Share::getBuyCount, query.getBuyCount());
        lqw.eq(query.getShowFlag() != null, Share::getShowFlag, query.getShowFlag());
        lqw.eq(StringUtils.isNotBlank(query.getAuditStatus()), Share::getAuditStatus, query.getAuditStatus());
        lqw.eq(StringUtils.isNotBlank(query.getReason()), Share::getReason, query.getReason());
        return lqw;
    }

//    public Share exchange(ExchangeDTO exchangeDTO) {
//        Long userId = exchangeDTO.getUserId();
//        Long shareId = exchangeDTO.getShareId();
//        //1.根据 id 查询 share 校验需要兑换的资源是否存在
//        Share share = shareMapper.selectById(shareId);
//        if (share == null) {
//            throw new IllegalArgumentException("该分享不存在！");
//        }
//
//        //2.如果当前用户已经兑换过该分享，则直接返回该分享（不需要扣除积分）
//        MidUserShare midUserShare = midUserShareMapper.selectOne(new QueryWrapper<MidUserShare>().lambda()
//                .eq(MidUserShare::getShareId, shareId)
//                .eq(MidUserShare::getUserId, userId));
//        if (midUserShare != null) return share;
//
//        //3.看用户积分是否足够
//        CommonResp<User> commonResp = userService.getUser(userId);
//        User user = commonResp.getData();
//        // 兑换这条资源需要的积分
//        Integer price = share.getPrice();
//        // 看积分是否足够
//        if (price > user.getBonus()) throw new IllegalArgumentException("用户积分不够！");
//
//        //4.修改积分（*-1 就是负值积分）
//        userService.updateBonus(UserAddBonusMsgDTO.builder().userId(userId).bonus(price * -1).build());
//        //5.向 mid_user_share 表插入一条数据，让这个用户对于这个资源拥有了下载权限
//        midUserShareMapper.insert(MidUserShare.builder().userId(userId).shareId(shareId).build());
//        return share;
//    }

}
