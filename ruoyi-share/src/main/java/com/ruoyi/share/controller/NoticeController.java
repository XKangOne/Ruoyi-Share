package com.ruoyi.share.controller;

import java.util.List;
import java.util.Arrays;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.share.domain.Notice;
import com.ruoyi.share.service.INoticeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 信息Controller
 *
 * @author ruoyi
 * @date 2023-11-14
 */
@Api(tags="信息管理")
@RestController
@RequestMapping("/share/notice")
public class NoticeController extends BaseController
{
    @Autowired
    private INoticeService noticeService;

    /**
     * 查询信息列表
     */
    @ApiOperation("查询信息列表")
    @PreAuthorize("@ss.hasPermi('share:notice:list')")
    @GetMapping("/list")
    public TableDataInfo list(Notice notice)
    {
        startPage();
        List<Notice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }

    /**
     * 导出信息列表
     */
    @ApiOperation("导出信息列表")
    @PreAuthorize("@ss.hasPermi('share:notice:export')")
    @Log(title = "信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Notice notice)
    {
        List<Notice> list = noticeService.selectNoticeList(notice);
        ExcelUtil<Notice> util = new ExcelUtil<Notice>(Notice.class);
        util.exportExcel(response, list, "信息数据");
    }

    /**
     * 获取信息详细信息
     */
    @ApiOperation("获取信息详细信息")
    @PreAuthorize("@ss.hasPermi('share:notice:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(noticeService.getById(id));
    }

    /**
     * 新增信息
     */
    @ApiOperation("新增信息")
    @PreAuthorize("@ss.hasPermi('share:notice:add')")
    @Log(title = "信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Notice notice)
    {
        return toAjax(noticeService.save(notice));
    }

    /**
     * 修改信息
     */
    @ApiOperation("修改信息")
    @PreAuthorize("@ss.hasPermi('share:notice:edit')")
    @Log(title = "信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Notice notice)
    {
        return toAjax(noticeService.updateById(notice));
    }

    /**
     * 删除信息
     */
    @ApiOperation("删除信息")
    @PreAuthorize("@ss.hasPermi('share:notice:remove')")
    @Log(title = "信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(noticeService.removeByIds(Arrays.asList(ids)));
    }
}
