package com.ruoyi.web.controller.project;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.domain.TowerFactory;
import com.ruoyi.project.service.ITowerFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 塔机生产厂家管理Controller
 *
 * @author
 * @date 2024-01-07
 */
@RestController
@RequestMapping("/project/tower_factory")
public class TowerFactoryController extends BaseController
{
    @Autowired
    private ITowerFactoryService towerFactoryService;

    /**
     * 查询塔机生产厂家管理列表
     */
    @PreAuthorize("@ss.hasPermi('project:tower_factory:list')")
    @GetMapping("/list")
    public TableDataInfo list(TowerFactory towerFactory)
    {
        startPage();
        List<TowerFactory> list = towerFactoryService.selectTowerFactoryList(towerFactory);
        return getDataTable(list);
    }

    /**
     * 导出塔机生产厂家管理列表
     */
    @PreAuthorize("@ss.hasPermi('project:tower_factory:export')")
    @Log(title = "塔机生产厂家管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TowerFactory towerFactory)
    {
        List<TowerFactory> list = towerFactoryService.selectTowerFactoryList(towerFactory);
        ExcelUtil<TowerFactory> util = new ExcelUtil<TowerFactory>(TowerFactory.class);
        util.exportExcel(response, list, "塔机生产厂家管理数据");
    }

    /**
     * 获取塔机生产厂家管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:tower_factory:query')")
    @GetMapping(value = "/{factoryId}")
    public AjaxResult getInfo(@PathVariable("factoryId") Long factoryId)
    {
        return AjaxResult.success(towerFactoryService.selectTowerFactoryByFactoryId(factoryId));
    }

    /**
     * 新增塔机生产厂家管理
     */
    @PreAuthorize("@ss.hasPermi('project:tower_factory:add')")
    @Log(title = "塔机生产厂家管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TowerFactory towerFactory)
    {
        return toAjax(towerFactoryService.insertTowerFactory(towerFactory));
    }

    /**
     * 修改塔机生产厂家管理
     */
    @PreAuthorize("@ss.hasPermi('project:tower_factory:edit')")
    @Log(title = "塔机生产厂家管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TowerFactory towerFactory)
    {
        return toAjax(towerFactoryService.updateTowerFactory(towerFactory));
    }

    /**
     * 删除塔机生产厂家管理
     */
    @PreAuthorize("@ss.hasPermi('project:tower_factory:remove')")
    @Log(title = "塔机生产厂家管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{factoryIds}")
    public AjaxResult remove(@PathVariable Long[] factoryIds)
    {
        return toAjax(towerFactoryService.deleteTowerFactoryByFactoryIds(factoryIds));
    }
}
