package com.ruoyi.web.controller.project;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.domain.ErrorTower;
import com.ruoyi.project.domain.ProjectTower;
import com.ruoyi.project.domain.Result;
import com.ruoyi.project.service.IErrorTowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 异常信息管理Controller
 * 
 * @author SWE20054橘子
 * @date 2024-03-31
 */
@RestController
@RequestMapping("/project/error_tower")
public class ErrorTowerController extends BaseController
{
    @Autowired
    private IErrorTowerService errorTowerService;

    /**
     * 查询异常信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('project:error_tower:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErrorTower errorTower)
    {
        startPage();
        List<ErrorTower> list = errorTowerService.selectErrorTowerList(errorTower);
        return getDataTable(list);
    }

    /**
     * 导出异常信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('project:error_tower:export')")
    @Log(title = "异常信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErrorTower errorTower)
    {
        List<ErrorTower> list = errorTowerService.selectErrorTowerList(errorTower);
        ExcelUtil<ErrorTower> util = new ExcelUtil<ErrorTower>(ErrorTower.class);
        util.exportExcel(response, list, "异常信息管理数据");
    }

    /**
     * 获取异常信息管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:error_tower:query')")
    @GetMapping(value = "/{errorId}")
    public AjaxResult getInfo(@PathVariable("errorId") Long errorId)
    {
        return AjaxResult.success(errorTowerService.selectErrorTowerByErrorId(errorId));
    }
    //4.14异常塔机信息详细列表
    @GetMapping("/site/{siteId}")
    public List<ErrorTower> getInfoSite(@PathVariable("siteId") Long siteId)
    {
      List<ErrorTower> ErrorTowerList =errorTowerService.selectErrorTowerBySiteId(siteId);
      return ErrorTowerList;
    }
    @GetMapping("/siteAll/{siteId}")
    public List<Result> getInfoSiteAll(@PathVariable("siteId") Long siteId)
    {
        List<Result> ResultList =errorTowerService.selectErrorTowerAllBySiteId(siteId);
        return ResultList;
    }
    /**
     * 新增异常信息管理
     */
//    @PreAuthorize("@ss.hasPermi('project:error_tower:add')")
    @Log(title = "异常信息管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErrorTower errorTower)
    {
        return toAjax(errorTowerService.insertErrorTower(errorTower));
    }

    /**
     * 修改异常信息管理
     */
    @PreAuthorize("@ss.hasPermi('project:error_tower:edit')")
    @Log(title = "异常信息管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErrorTower errorTower)
    {
        return toAjax(errorTowerService.updateErrorTower(errorTower));
    }

    /**
     * 删除异常信息管理
     */
    @PreAuthorize("@ss.hasPermi('project:error_tower:remove')")
    @Log(title = "异常信息管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{errorIds}")
    public AjaxResult remove(@PathVariable Long[] errorIds)
    {
        return toAjax(errorTowerService.deleteErrorTowerByErrorIds(errorIds));
    }
}
