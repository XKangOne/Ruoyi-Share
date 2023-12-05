package com.ruoyi.share.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.common.utils.StringUtils;
import java.util.List;
import java.util.Map;
import com.ruoyi.share.mapper.NoticeMapper;
import com.ruoyi.share.domain.Notice;
import com.ruoyi.share.service.INoticeService;

/**
 * 信息Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-14
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 查询信息列表
     *
     * @param notice 信息
     * @return 信息
     */
    @Override
    public List<Notice> selectNoticeList(Notice notice)
    {
        List<Notice> noticeList = noticeMapper.selectList(buildQueryWrapper(notice));
        return noticeList;
    }


    private LambdaQueryWrapper<Notice> buildQueryWrapper(Notice query) {
        Map<String, Object> params = query.getParams();
        LambdaQueryWrapper<Notice> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(query.getContent()), Notice::getContent, query.getContent());
        lqw.eq(query.getShowFlag() != null, Notice::getShowFlag, query.getShowFlag());
        lqw.orderByDesc(Notice::getCreateTime);
        return lqw;
    }

}
