package com.ruoyi.web.controller.project;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.domain.Option;
import com.ruoyi.project.domain.ProjectTower;
import com.ruoyi.project.service.IProjectTowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 塔机管理Controller
 *
 * @author
 * @date 2024-01-08
 */
@RestController
@RequestMapping("/project/tower")
public class ProjectTowerController extends BaseController
{
    @Autowired
    private IProjectTowerService projectTowerService;

    /**
     * 查询塔机管理列表
     */
    @PreAuthorize("@ss.hasPermi('project:tower:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectTower projectTower)
    {
        startPage();
        List<ProjectTower> list = projectTowerService.selectProjectTowerList(projectTower);
        return getDataTable(list);
    }
    //3.24新增
    /**
     * 查询塔机状态列表以及数量
     */
    @PreAuthorize("@ss.hasPermi('dv:tower_display:list')")
    @GetMapping("/statusList")
    public TableDataInfo statusList(ProjectTower projectTower)
    {
       List<ProjectTower> list = projectTowerService.selectProjectTowerStatusList(projectTower);
       return getDataTable(list);
    }
    /**
     * 查询塔机分布列表以及数量
     */
    @PreAuthorize("@ss.hasPermi('dv:tower_display:list')")
    @GetMapping("/distributionList")
    public TableDataInfo distributionList(ProjectTower projectTower)
    {
        List<ProjectTower> list = projectTowerService.selectProjectTowerDistributionList(projectTower);
        return getDataTable(list);
    }
    //3.25新增
    /**
     * 查询现有塔机制造商列表以及数量
     */
    @PreAuthorize("@ss.hasPermi('dv:tower_display:list')")
    @GetMapping("/contractorsList")
    public TableDataInfo contractorsList(ProjectTower projectTower)
    {
        List<ProjectTower> list = projectTowerService.selectProjectTowerContractorsList(projectTower);
        System.out.println(list);
        return getDataTable(list);
    }
//3.27新增
/**
 * 获取塔机选择选项数据
 */
    @PreAuthorize("@ss.hasPermi('dv:tower_display:list')")
    @GetMapping("/towerOptions")
    public TableDataInfo towerOptions()
    {
        List<Option> list = projectTowerService.selectProjectTowerOptionsList();
        System.out.println(list);
        return getDataTable(list);
    }
    //
    /**
     * 导出塔机管理列表
     */
    @PreAuthorize("@ss.hasPermi('project:tower:export')")
    @Log(title = "塔机管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectTower projectTower)
    {
        List<ProjectTower> list = projectTowerService.selectProjectTowerList(projectTower);
        ExcelUtil<ProjectTower> util = new ExcelUtil<ProjectTower>(ProjectTower.class);
        util.exportExcel(response, list, "塔机管理数据");
    }

    /**
     * 获取塔机管理详细信息
     */
//    @PreAuthorize("@ss.hasPermi('project:tower:query')")
    @GetMapping(value = "/{towerId}")
    public AjaxResult getInfo(@PathVariable("towerId") Long towerId)
    {
        return AjaxResult.success(projectTowerService.selectProjectTowerByTowerId(towerId));
    }
    //4.14获取工地所属塔机数据
    /**
     * 获取工地所属塔机数据
     */
//    @PreAuthorize("@ss.hasPermi('project:tower:query')")
    @GetMapping("/site/{siteId}")
    public List<ProjectTower> getInfoSite(@PathVariable("siteId") Long siteId)
    {
        List<ProjectTower> projectTowerList =projectTowerService.selectProjectTowerBySiteId(siteId);
        return projectTowerList;
    }

    /**
     * 新增塔机管理
     */
    @PreAuthorize("@ss.hasPermi('project:tower:add')")
    @Log(title = "塔机管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectTower projectTower)
    {
        return toAjax(projectTowerService.insertProjectTower(projectTower));
    }

    /**
     * 修改塔机管理
     */
    @PreAuthorize("@ss.hasPermi('project:tower:edit')")
    @Log(title = "塔机管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectTower projectTower)
    {
        return toAjax(projectTowerService.updateProjectTower(projectTower));
    }

    /**
     * 删除塔机管理
     */
    @PreAuthorize("@ss.hasPermi('project:tower:remove')")
    @Log(title = "塔机管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{towerIds}")
    public AjaxResult remove(@PathVariable Long[] towerIds)
    {
        return toAjax(projectTowerService.deleteProjectTowerByTowerIds(towerIds));
    }
}
