package com.ruoyi.project.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.mapper.SiteWeatherMapper;
import com.ruoyi.project.domain.SiteWeather;
import com.ruoyi.project.service.ISiteWeatherService;

/**
 * 工地环境情况管理Service业务层处理
 * 
 * @author SWE20054林坤城
 * @date 2024-04-15
 */
@Service
public class SiteWeatherServiceImpl implements ISiteWeatherService 
{
    @Autowired
    private SiteWeatherMapper siteWeatherMapper;

    /**
     * 查询工地环境情况管理
     * 
     * @param siteId 工地环境情况管理主键
     * @return 工地环境情况管理
     */
    @Override
    public SiteWeather selectSiteWeatherBySiteId(Long siteId)
    {
        return siteWeatherMapper.selectSiteWeatherBySiteId(siteId);
    }
//4.15新增
    @Override
    public SiteWeather selectToDaySiteWeatherBySiteId(Long siteId) {
        return siteWeatherMapper.selectToDaySiteWeatherBySiteId(siteId);
    }

    /**
     * 查询工地环境情况管理列表
     * 
     * @param siteWeather 工地环境情况管理
     * @return 工地环境情况管理
     */
    @Override
    public List<SiteWeather> selectSiteWeatherList(SiteWeather siteWeather)
    {
        return siteWeatherMapper.selectSiteWeatherList(siteWeather);
    }

    /**
     * 新增工地环境情况管理
     * 
     * @param siteWeather 工地环境情况管理
     * @return 结果
     */
    @Override
    public int insertSiteWeather(SiteWeather siteWeather)
    {
        return siteWeatherMapper.insertSiteWeather(siteWeather);
    }

    /**
     * 修改工地环境情况管理
     * 
     * @param siteWeather 工地环境情况管理
     * @return 结果
     */
    @Override
    public int updateSiteWeather(SiteWeather siteWeather)
    {
        return siteWeatherMapper.updateSiteWeather(siteWeather);
    }

    /**
     * 批量删除工地环境情况管理
     * 
     * @param siteIds 需要删除的工地环境情况管理主键
     * @return 结果
     */
    @Override
    public int deleteSiteWeatherBySiteIds(Long[] siteIds)
    {
        return siteWeatherMapper.deleteSiteWeatherBySiteIds(siteIds);
    }

    /**
     * 删除工地环境情况管理信息
     * 
     * @param siteId 工地环境情况管理主键
     * @return 结果
     */
    @Override
    public int deleteSiteWeatherBySiteId(Long siteId)
    {
        return siteWeatherMapper.deleteSiteWeatherBySiteId(siteId);
    }
}
