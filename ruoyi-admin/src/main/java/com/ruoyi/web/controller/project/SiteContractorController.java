package com.ruoyi.web.controller.project;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.domain.SiteContractor;
import com.ruoyi.project.service.ISiteContractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 工地承包商管理Controller
 *
 * @author
 * @date 2024-01-07
 */
@RestController
@RequestMapping("/project/site_contractor")
public class SiteContractorController extends BaseController
{
    @Autowired
    private ISiteContractorService siteContractorService;

    /**
     * 查询工地承包商管理列表
     */
    @PreAuthorize("@ss.hasPermi('project:site_contractor:list')")
    @GetMapping("/list")
    public TableDataInfo list(SiteContractor siteContractor)
    {
        startPage();
        List<SiteContractor> list = siteContractorService.selectSiteContractorList(siteContractor);
        return getDataTable(list);
    }

    /**
     * 导出工地承包商管理列表
     */
    @PreAuthorize("@ss.hasPermi('project:site_contractor:export')")
    @Log(title = "工地承包商管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SiteContractor siteContractor)
    {
        List<SiteContractor> list = siteContractorService.selectSiteContractorList(siteContractor);
        ExcelUtil<SiteContractor> util = new ExcelUtil<SiteContractor>(SiteContractor.class);
        util.exportExcel(response, list, "工地承包商管理数据");
    }

    /**
     * 获取工地承包商管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:site_contractor:query')")
    @GetMapping(value = "/{contractorId}")
    public AjaxResult getInfo(@PathVariable("contractorId") Long contractorId)
    {
        return AjaxResult.success(siteContractorService.selectSiteContractorByContractorId(contractorId));
    }

    /**
     * 新增工地承包商管理
     */
    @PreAuthorize("@ss.hasPermi('project:site_contractor:add')")
    @Log(title = "工地承包商管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SiteContractor siteContractor)
    {
        return toAjax(siteContractorService.insertSiteContractor(siteContractor));
    }

    /**
     * 修改工地承包商管理
     */
    @PreAuthorize("@ss.hasPermi('project:site_contractor:edit')")
    @Log(title = "工地承包商管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SiteContractor siteContractor)
    {
        return toAjax(siteContractorService.updateSiteContractor(siteContractor));
    }

    /**
     * 删除工地承包商管理
     */
    @PreAuthorize("@ss.hasPermi('project:site_contractor:remove')")
    @Log(title = "工地承包商管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{contractorIds}")
    public AjaxResult remove(@PathVariable Long[] contractorIds)
    {
        return toAjax(siteContractorService.deleteSiteContractorByContractorIds(contractorIds));
    }
}
