package com.ruoyi.project.service;

import java.util.List;
import com.ruoyi.project.domain.SiteWeather;

/**
 * 工地环境情况管理Service接口
 * 
 * @author SWE20054林坤城
 * @date 2024-04-15
 */
public interface ISiteWeatherService 
{
    /**
     * 查询工地环境情况管理
     * 
     * @param siteId 工地环境情况管理主键
     * @return 工地环境情况管理
     */
    public SiteWeather selectSiteWeatherBySiteId(Long siteId);
    public SiteWeather selectToDaySiteWeatherBySiteId(Long siteId);

    /**
     * 查询工地环境情况管理列表
     * 
     * @param siteWeather 工地环境情况管理
     * @return 工地环境情况管理集合
     */
    public List<SiteWeather> selectSiteWeatherList(SiteWeather siteWeather);

    /**
     * 新增工地环境情况管理
     * 
     * @param siteWeather 工地环境情况管理
     * @return 结果
     */
    public int insertSiteWeather(SiteWeather siteWeather);

    /**
     * 修改工地环境情况管理
     * 
     * @param siteWeather 工地环境情况管理
     * @return 结果
     */
    public int updateSiteWeather(SiteWeather siteWeather);

    /**
     * 批量删除工地环境情况管理
     * 
     * @param siteIds 需要删除的工地环境情况管理主键集合
     * @return 结果
     */
    public int deleteSiteWeatherBySiteIds(Long[] siteIds);

    /**
     * 删除工地环境情况管理信息
     * 
     * @param siteId 工地环境情况管理主键
     * @return 结果
     */
    public int deleteSiteWeatherBySiteId(Long siteId);
}
