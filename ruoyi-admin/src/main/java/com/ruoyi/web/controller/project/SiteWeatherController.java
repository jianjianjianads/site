package com.ruoyi.web.controller.project;

import java.util.List;
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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.project.domain.SiteWeather;
import com.ruoyi.project.service.ISiteWeatherService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工地环境情况管理Controller
 *
 * @author
 * @date 2024-04-15
 */
@RestController
@RequestMapping("/project/weather")
public class SiteWeatherController extends BaseController
{
    @Autowired
    private ISiteWeatherService siteWeatherService;

    /**
     * 查询工地环境情况管理列表
     */
    @PreAuthorize("@ss.hasPermi('project:weather:list')")
    @GetMapping("/list")
    public TableDataInfo list(SiteWeather siteWeather)
    {
        startPage();
        List<SiteWeather> list = siteWeatherService.selectSiteWeatherList(siteWeather);
        return getDataTable(list);
    }

    /**
     * 导出工地环境情况管理列表
     */
    @PreAuthorize("@ss.hasPermi('project:weather:export')")
    @Log(title = "工地环境情况管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SiteWeather siteWeather)
    {
        List<SiteWeather> list = siteWeatherService.selectSiteWeatherList(siteWeather);
        ExcelUtil<SiteWeather> util = new ExcelUtil<SiteWeather>(SiteWeather.class);
        util.exportExcel(response, list, "工地环境情况管理数据");
    }

    /**
     * 获取工地环境情况管理详细信息
     */
//    @PreAuthorize("@ss.hasPermi('project:weather:query')")
    @GetMapping(value = "/{siteId}")
    public AjaxResult getInfo(@PathVariable("siteId") Long siteId)
    {
        return AjaxResult.success(siteWeatherService.selectSiteWeatherBySiteId(siteId));
    }
   //4.15新增获取单日塔机环境情况
   @GetMapping("/site/{siteId}")
   public AjaxResult getInfoSite(@PathVariable("siteId") Long siteId)
   {
    return AjaxResult.success(siteWeatherService.selectToDaySiteWeatherBySiteId(siteId));
   }
    /**
     * 新增工地环境情况管理
     */
    @PreAuthorize("@ss.hasPermi('project:weather:add')")
    @Log(title = "工地环境情况管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SiteWeather siteWeather)
    {
        return toAjax(siteWeatherService.insertSiteWeather(siteWeather));
    }

    /**
     * 修改工地环境情况管理
     */
    @PreAuthorize("@ss.hasPermi('project:weather:edit')")
    @Log(title = "工地环境情况管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SiteWeather siteWeather)
    {
        return toAjax(siteWeatherService.updateSiteWeather(siteWeather));
    }

    /**
     * 删除工地环境情况管理
     */
    @PreAuthorize("@ss.hasPermi('project:weather:remove')")
    @Log(title = "工地环境情况管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{siteIds}")
    public AjaxResult remove(@PathVariable Long[] siteIds)
    {
        return toAjax(siteWeatherService.deleteSiteWeatherBySiteIds(siteIds));
    }
}
