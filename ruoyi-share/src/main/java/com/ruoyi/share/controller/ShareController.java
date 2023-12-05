package com.ruoyi.share.controller;

import java.util.List;
import java.util.Arrays;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.config.ServerConfig;
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
import com.ruoyi.share.domain.Share;
import com.ruoyi.share.service.IShareService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 分享Controller
 *
 * @author ruoyi
 * @date 2023-11-14
 */
@Api(tags="分享管理")
@RestController
@RequestMapping("/share/sha")
public class ShareController extends BaseController
{
    @Autowired
    private IShareService shareService;



    /**
     * 查询分享列表
     */
    @ApiOperation("查询分享列表")
    @PreAuthorize("@ss.hasPermi('share:sha:list')")
    @GetMapping("/list")
    public TableDataInfo list(Share share)
    {
        startPage();
        List<Share> list = shareService.selectShareList(share);
        return getDataTable(list);
    }

    /**
     * 导出分享列表
     */
    @ApiOperation("导出分享列表")
    @PreAuthorize("@ss.hasPermi('share:sha:export')")
    @Log(title = "分享", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Share share)
    {
        List<Share> list = shareService.selectShareList(share);
        ExcelUtil<Share> util = new ExcelUtil<Share>(Share.class);
        util.exportExcel(response, list, "分享数据");
    }

    /**
     * 获取分享详细信息
     */
    @ApiOperation("获取分享详细信息")
    @PreAuthorize("@ss.hasPermi('share:sha:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(shareService.getById(id));
    }

    /**
     * 新增分享
     */
    @ApiOperation("新增分享")
    @PreAuthorize("@ss.hasPermi('share:sha:add')")
    @Log(title = "分享", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Share share)
    {
        return toAjax(shareService.save(share));
    }

    /**
     * 修改分享
     */
    @ApiOperation("修改分享")
    @PreAuthorize("@ss.hasPermi('share:sha:edit')")
    @Log(title = "分享", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Share share)
    {
        return toAjax(shareService.updateById(share));
    }

    /**
     * 删除分享
     */
    @ApiOperation("删除分享")
    @PreAuthorize("@ss.hasPermi('share:sha:remove')")
    @Log(title = "分享", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(shareService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * 通用上传请求
     */
    @Anonymous
    @PostMapping("/uploadCover")
    public AjaxResult cover(MultipartFile file) throws Exception
    {
        System.out.println(file);
        if (!file.isEmpty())
        {
            String cover = FileUploadUtils.upload(RuoYiConfig.getCoverPath(), file);
            System.out.println(cover);
            AjaxResult ajax = AjaxResult.success();
            ajax.put("coverUrl", cover);
            return ajax;
        }
        return error("上传图片异常，请联系管理员");
    }
}
