package com.ruoyi.web.controller.project;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.domain.ElevatorFactory;
import com.ruoyi.project.service.IElevatorFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 电梯生产厂家管理Controller
 * 
 * @author SWE20054
 * @date 2024-01-06
 */
@RestController
@RequestMapping("/project/elevator_factory")
public class ElevatorFactoryController extends BaseController
{
    @Autowired
    private IElevatorFactoryService elevatorFactoryService;

    /**
     * 查询电梯生产厂家管理列表
     */
    @PreAuthorize("@ss.hasPermi('project:elevator_factory:list')")
    @GetMapping("/list")
    public TableDataInfo list(ElevatorFactory elevatorFactory)
    {
        startPage();
        List<ElevatorFactory> list = elevatorFactoryService.selectElevatorFactoryList(elevatorFactory);
        return getDataTable(list);
    }

    /**
     * 导出电梯生产厂家管理列表
     */
    @PreAuthorize("@ss.hasPermi('project:elevator_factory:export')")
    @Log(title = "电梯生产厂家管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ElevatorFactory elevatorFactory)
    {
        List<ElevatorFactory> list = elevatorFactoryService.selectElevatorFactoryList(elevatorFactory);
        ExcelUtil<ElevatorFactory> util = new ExcelUtil<ElevatorFactory>(ElevatorFactory.class);
        util.exportExcel(response, list, "电梯生产厂家管理数据");
    }

    /**
     * 获取电梯生产厂家管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:elevator_factory:query')")
    @GetMapping(value = "/{factoryId}")
    public AjaxResult getInfo(@PathVariable("factoryId") Long factoryId)
    {
        return AjaxResult.success(elevatorFactoryService.selectElevatorFactoryByFactoryId(factoryId));
    }

    /**
     * 新增电梯生产厂家管理
     */
    @PreAuthorize("@ss.hasPermi('project:elevator_factory:add')")
    @Log(title = "电梯生产厂家管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ElevatorFactory elevatorFactory)
    {
        return toAjax(elevatorFactoryService.insertElevatorFactory(elevatorFactory));
    }

    /**
     * 修改电梯生产厂家管理
     */
    @PreAuthorize("@ss.hasPermi('project:elevator_factory:edit')")
    @Log(title = "电梯生产厂家管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ElevatorFactory elevatorFactory)
    {
        return toAjax(elevatorFactoryService.updateElevatorFactory(elevatorFactory));
    }

    /**
     * 删除电梯生产厂家管理
     */
    @PreAuthorize("@ss.hasPermi('project:elevator_factory:remove')")
    @Log(title = "电梯生产厂家管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{factoryIds}")
    public AjaxResult remove(@PathVariable Long[] factoryIds)
    {
        return toAjax(elevatorFactoryService.deleteElevatorFactoryByFactoryIds(factoryIds));
    }
}
