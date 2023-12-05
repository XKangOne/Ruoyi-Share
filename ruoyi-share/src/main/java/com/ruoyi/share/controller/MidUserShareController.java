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
import com.ruoyi.share.domain.MidUserShare;
import com.ruoyi.share.service.IMidUserShareService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户-分享中间【描述用户购买的分享】Controller
 *
 * @author ruoyi
 * @date 2023-11-14
 */
@Api(tags="用户-分享中间【描述用户购买的分享】管理")
@RestController
@RequestMapping("/share/midsh")
public class MidUserShareController extends BaseController
{
    @Autowired
    private IMidUserShareService midUserShareService;

    /**
     * 查询用户-分享中间【描述用户购买的分享】列表
     */
    @ApiOperation("查询用户-分享中间【描述用户购买的分享】列表")
    @PreAuthorize("@ss.hasPermi('share:midsh:list')")
    @GetMapping("/list")
    public TableDataInfo list(MidUserShare midUserShare)
    {
        startPage();
        List<MidUserShare> list = midUserShareService.selectMidUserShareList(midUserShare);
        return getDataTable(list);
    }

    /**
     * 导出用户-分享中间【描述用户购买的分享】列表
     */
    @ApiOperation("导出用户-分享中间【描述用户购买的分享】列表")
    @PreAuthorize("@ss.hasPermi('share:midsh:export')")
    @Log(title = "用户-分享中间【描述用户购买的分享】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MidUserShare midUserShare)
    {
        List<MidUserShare> list = midUserShareService.selectMidUserShareList(midUserShare);
        ExcelUtil<MidUserShare> util = new ExcelUtil<MidUserShare>(MidUserShare.class);
        util.exportExcel(response, list, "用户-分享中间【描述用户购买的分享】数据");
    }

    /**
     * 获取用户-分享中间【描述用户购买的分享】详细信息
     */
    @ApiOperation("获取用户-分享中间【描述用户购买的分享】详细信息")
    @PreAuthorize("@ss.hasPermi('share:midsh:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(midUserShareService.getById(id));
    }

    /**
     * 新增用户-分享中间【描述用户购买的分享】
     */
    @ApiOperation("新增用户-分享中间【描述用户购买的分享】")
    @PreAuthorize("@ss.hasPermi('share:midsh:add')")
    @Log(title = "用户-分享中间【描述用户购买的分享】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MidUserShare midUserShare)
    {
        return toAjax(midUserShareService.save(midUserShare));
    }

    /**
     * 修改用户-分享中间【描述用户购买的分享】
     */
    @ApiOperation("修改用户-分享中间【描述用户购买的分享】")
    @PreAuthorize("@ss.hasPermi('share:midsh:edit')")
    @Log(title = "用户-分享中间【描述用户购买的分享】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MidUserShare midUserShare)
    {
        return toAjax(midUserShareService.updateById(midUserShare));
    }

    /**
     * 删除用户-分享中间【描述用户购买的分享】
     */
    @ApiOperation("删除用户-分享中间【描述用户购买的分享】")
    @PreAuthorize("@ss.hasPermi('share:midsh:remove')")
    @Log(title = "用户-分享中间【描述用户购买的分享】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(midUserShareService.removeByIds(Arrays.asList(ids)));
    }
}
