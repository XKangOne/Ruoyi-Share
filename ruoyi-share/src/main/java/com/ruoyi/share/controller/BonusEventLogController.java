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
import com.ruoyi.share.domain.BonusEventLog;
import com.ruoyi.share.service.IBonusEventLogService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 积分变更记录Controller
 *
 * @author ruoyi
 * @date 2023-11-14
 */
@Api(tags="积分变更记录管理")
@RestController
@RequestMapping("/share/log")
public class BonusEventLogController extends BaseController
{
    @Autowired
    private IBonusEventLogService bonusEventLogService;

    /**
     * 查询积分变更记录列表
     */
    @ApiOperation("查询积分变更记录列表")
    @PreAuthorize("@ss.hasPermi('share:log:list')")
    @GetMapping("/list")
    public TableDataInfo list(BonusEventLog bonusEventLog)
    {
        startPage();
        List<BonusEventLog> list = bonusEventLogService.selectBonusEventLogList(bonusEventLog);
        return getDataTable(list);
    }

    /**
     * 导出积分变更记录列表
     */
    @ApiOperation("导出积分变更记录列表")
    @PreAuthorize("@ss.hasPermi('share:log:export')")
    @Log(title = "积分变更记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BonusEventLog bonusEventLog)
    {
        List<BonusEventLog> list = bonusEventLogService.selectBonusEventLogList(bonusEventLog);
        ExcelUtil<BonusEventLog> util = new ExcelUtil<BonusEventLog>(BonusEventLog.class);
        util.exportExcel(response, list, "积分变更记录数据");
    }

    /**
     * 获取积分变更记录详细信息
     */
    @ApiOperation("获取积分变更记录详细信息")
    @PreAuthorize("@ss.hasPermi('share:log:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bonusEventLogService.getById(id));
    }

    /**
     * 新增积分变更记录
     */
    @ApiOperation("新增积分变更记录")
    @PreAuthorize("@ss.hasPermi('share:log:add')")
    @Log(title = "积分变更记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BonusEventLog bonusEventLog)
    {
        return toAjax(bonusEventLogService.save(bonusEventLog));
    }

    /**
     * 修改积分变更记录
     */
    @ApiOperation("修改积分变更记录")
    @PreAuthorize("@ss.hasPermi('share:log:edit')")
    @Log(title = "积分变更记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BonusEventLog bonusEventLog)
    {
        return toAjax(bonusEventLogService.updateById(bonusEventLog));
    }

    /**
     * 删除积分变更记录
     */
    @ApiOperation("删除积分变更记录")
    @PreAuthorize("@ss.hasPermi('share:log:remove')")
    @Log(title = "积分变更记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bonusEventLogService.removeByIds(Arrays.asList(ids)));
    }
}
