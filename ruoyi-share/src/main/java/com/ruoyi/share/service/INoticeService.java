package com.ruoyi.share.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.share.domain.Notice;

/**
 * 信息Service接口
 *
 * @author ruoyi
 * @date 2023-11-14
 */
public interface INoticeService extends IService<Notice> {

    /**
     * 查询信息列表
     *
     * @param notice 信息
     * @return 信息集合
     */
    public List<Notice> selectNoticeList(Notice notice);

}
