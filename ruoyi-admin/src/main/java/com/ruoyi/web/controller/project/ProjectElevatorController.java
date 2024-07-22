package com.ruoyi.web.controller.project;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.domain.ProjectElevator;
import com.ruoyi.project.service.IProjectElevatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 工程电梯管理Controller
 * 
 * @author SWE20054
 * @date 2024-01-05
 */
@RestController
@RequestMapping("/project/elevator")
public class ProjectElevatorController extends BaseController
{
    @Autowired
    private IProjectElevatorService projectElevatorService;

    /**
     * 查询工程电梯管理列表
     */
    @PreAuthorize("@ss.hasPermi('project:elevator:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectElevator projectElevator)
    {
        startPage();
        List<ProjectElevator> list = projectElevatorService.selectProjectElevatorList(projectElevator);
        return getDataTable(list);
    }

    /**
     * 导出工程电梯管理列表
     */
    @PreAuthorize("@ss.hasPermi('project:elevator:export')")
    @Log(title = "工程电梯管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectElevator projectElevator)
    {
        List<ProjectElevator> list = projectElevatorService.selectProjectElevatorList(projectElevator);
        ExcelUtil<ProjectElevator> util = new ExcelUtil<ProjectElevator>(ProjectElevator.class);
        util.exportExcel(response, list, "工程电梯管理数据");
    }

    /**
     * 获取工程电梯管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:elevator:query')")
    @GetMapping(value = "/{elevatorId}")
    public AjaxResult getInfo(@PathVariable("elevatorId") Long elevatorId)
    {
        return AjaxResult.success(projectElevatorService.selectProjectElevatorByElevatorId(elevatorId));
    }

    /**
     * 新增工程电梯管理
     */
    @PreAuthorize("@ss.hasPermi('project:elevator:add')")
    @Log(title = "工程电梯管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectElevator projectElevator)
    {
        return toAjax(projectElevatorService.insertProjectElevator(projectElevator));
    }

    /**
     * 修改工程电梯管理
     */
    @PreAuthorize("@ss.hasPermi('project:elevator:edit')")
    @Log(title = "工程电梯管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectElevator projectElevator)
    {
        return toAjax(projectElevatorService.updateProjectElevator(projectElevator));
    }

    /**
     * 删除工程电梯管理
     */
    @PreAuthorize("@ss.hasPermi('project:elevator:remove')")
    @Log(title = "工程电梯管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{elevatorIds}")
    public AjaxResult remove(@PathVariable Long[] elevatorIds)
    {
        return toAjax(projectElevatorService.deleteProjectElevatorByElevatorIds(elevatorIds));
    }
}
