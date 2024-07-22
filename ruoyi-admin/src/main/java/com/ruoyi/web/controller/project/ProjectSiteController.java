package com.ruoyi.web.controller.project;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.domain.Option;
import com.ruoyi.project.domain.ProjectSite;
import com.ruoyi.project.domain.Result;
import com.ruoyi.project.service.IProjectSiteService;
import io.netty.util.internal.ObjectUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

import static com.ruoyi.common.utils.StringUtils.isNotEmpty;
import static io.netty.util.internal.ObjectUtil.*;

/**
 * 工地项目Controller
 * 
 * @author SWE20054
 * @date 2024-01-07
 */
@RestController
@RequestMapping("/project/site")
public class ProjectSiteController extends BaseController
{
    @Autowired
    private IProjectSiteService projectSiteService;

    /**
     * 查询工地项目列表
     */
    @PreAuthorize("@ss.hasPermi('project:site:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectSite projectSite)
    {
        startPage();
        List<ProjectSite> list = projectSiteService.selectProjectSiteList(projectSite);
        return getDataTable(list);
    }
//3.23改动
    /**
     * 查询工地项目类型及其数量列表
     */
    @PreAuthorize("@ss.hasPermi('dv:site_display:list')")
    @GetMapping("/typeList")
    public TableDataInfo typeList(ProjectSite projectSite)
    {
        List<ProjectSite> list = projectSiteService.selectProjectSiteTypeList(projectSite);
        return getDataTable(list);
    }
//3.24新增
    /**
     * 查询工地项目状态及其数量列表
     */
    @PreAuthorize("@ss.hasPermi('dv:site_display:list')")
    @GetMapping("/statusList")
    public TableDataInfo statusList(ProjectSite projectSite)
    {
        List<ProjectSite> list = projectSiteService.selectProjectSiteStatusList(projectSite);
        return getDataTable(list);
    }
    //3.26新增
    /**
     * 获取工地项目预算分布
     */
    @PreAuthorize("@ss.hasPermi('dv:site_display:list')")
    @GetMapping("/budgetList")
    public TableDataInfo budgetList()
    {
        List<Result> list = projectSiteService.selectProjectSiteBudgetList();
        return getDataTable(list);
    }
    //3.26新增获取项目进度top5
    /**
     * 获取工地项目预算分布
     */
    /**
     * 查询工地项目状态及其数量列表
     */
    @PreAuthorize("@ss.hasPermi('dv:site_display:list')")
    @GetMapping("/progressList")
    public TableDataInfo progressList(ProjectSite projectSite)
    {
        List<ProjectSite> list = projectSiteService.selectProjectSiteProgressList(projectSite);
        return getDataTable(list);
    }
    //4.13新增
    /**
     * 获取工地选择选项数据
     */
    @PreAuthorize("@ss.hasPermi('dv:site_display:list')")
    @GetMapping("/siteOptions")
    public TableDataInfo siteOptions()
    {
        List<ProjectSite> list = projectSiteService.selectProjectSiteOptionsList();
        System.out.println(list);
        return getDataTable(list);
    }
    //

    /**
     * 导出工地项目列表
     */
    @PreAuthorize("@ss.hasPermi('project:site:export')")
    @Log(title = "工地项目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectSite projectSite)
    {
        List<ProjectSite> list = projectSiteService.selectProjectSiteList(projectSite);
        ExcelUtil<ProjectSite> util = new ExcelUtil<ProjectSite>(ProjectSite.class);
        util.exportExcel(response, list, "工地项目数据");
    }

    /**
     * 获取工地项目详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:site:query')")
    @GetMapping(value = "/{siteId}")
    public AjaxResult getInfo(@PathVariable("siteId") Long siteId)
    {
        return AjaxResult.success(projectSiteService.selectProjectSiteBySiteId(siteId));
    }

    /**
     * 新增工地项目
     */
    @PreAuthorize("@ss.hasPermi('project:site:add')")
    @Log(title = "工地项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectSite projectSite)
    {
        return toAjax(projectSiteService.insertProjectSite(projectSite));
    }

    /**
     * 修改工地项目
     */
    @PreAuthorize("@ss.hasPermi('project:site:edit')")
    @Log(title = "工地项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectSite projectSite)
    {
        return toAjax(projectSiteService.updateProjectSite(projectSite));
    }

    /**
     * 删除工地项目
     */
    @PreAuthorize("@ss.hasPermi('project:site:remove')")
    @Log(title = "工地项目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{siteIds}")
    public AjaxResult remove(@PathVariable Long[] siteIds)
    {
        return toAjax(projectSiteService.deleteProjectSiteBySiteIds(siteIds));
    }
}
