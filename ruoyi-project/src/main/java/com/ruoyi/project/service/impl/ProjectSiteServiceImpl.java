package com.ruoyi.project.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.mapper.ProjectSiteMapper;
import com.ruoyi.project.domain.ProjectSite;
import com.ruoyi.project.service.IProjectSiteService;

/**
 * 工地项目Service业务层处理
 * 
 * @author SWE20054
 * @date 2024-01-07
 */
@Service
public class ProjectSiteServiceImpl implements IProjectSiteService 
{
    @Autowired
    private ProjectSiteMapper projectSiteMapper;

    /**
     * 查询工地项目
     * 
     * @param siteId 工地项目主键
     * @return 工地项目
     */
    @Override
    public ProjectSite selectProjectSiteBySiteId(Long siteId)
    {
        return projectSiteMapper.selectProjectSiteBySiteId(siteId);
    }

    /**
     * 查询工地项目列表
     * 
     * @param projectSite 工地项目
     * @return 工地项目
     */
    @Override
    public List<ProjectSite> selectProjectSiteList(ProjectSite projectSite)
    {
        return projectSiteMapper.selectProjectSiteList(projectSite);
    }

    //3.23修改
    /**
     查询工地项目类型及其数量列表
     *
     * @param projectSite 工地项目
     * @return 工地项目集合
     */
    @Override
    public List<ProjectSite> selectProjectSiteTypeList(ProjectSite projectSite) {
        return projectSiteMapper.selectProjectSiteTypeList(projectSite);
    }
    //3.24新增接口
    /**
     查询工地项目状态及其数量列表
     *
     * @param projectSite 工地项目
     * @return 工地项目集合
     */
    @Override
    public List<ProjectSite> selectProjectSiteStatusList(ProjectSite projectSite) {
        return projectSiteMapper.selectProjectSiteStatusList(projectSite);
    }

    @Override
    public List<ProjectSite> selectProjectSiteProgressList(ProjectSite projectSite) {
        return projectSiteMapper.selectProjectSiteProgressList(projectSite);
    }

    @Override
    public List<ProjectSite> selectProjectSiteOptionsList() {
        return projectSiteMapper.selectProjectSiteOptionList();
    }

    //3.25新增获取工地预算分布
    /**
     查询工地项目状态及其数量列表
     *
     * @return 工地预算分布集合
     */
    @Override
    public List<Result> selectProjectSiteBudgetList() {
        return projectSiteMapper.selectProjectSiteBudgetList();
    }
    //3.26新增获取工地进度top
    /**
     查询工地项目状态及其数量列表
     *
     * @return 工地预算分布集合
     */

    /**
     * 新增工地项目
     * 
     * @param projectSite 工地项目
     * @return 结果
     */
    @Override
    public int insertProjectSite(ProjectSite projectSite)
    {
        projectSite.setCreateTime(DateUtils.getNowDate());
        return projectSiteMapper.insertProjectSite(projectSite);
    }

    /**
     * 修改工地项目
     * 
     * @param projectSite 工地项目
     * @return 结果
     */
    @Override
    public int updateProjectSite(ProjectSite projectSite)
    {
        projectSite.setUpdateTime(DateUtils.getNowDate());
        return projectSiteMapper.updateProjectSite(projectSite);
    }

    /**
     * 批量删除工地项目
     * 
     * @param siteIds 需要删除的工地项目主键
     * @return 结果
     */
    @Override
    public int deleteProjectSiteBySiteIds(Long[] siteIds)
    {
        return projectSiteMapper.deleteProjectSiteBySiteIds(siteIds);
    }

    /**
     * 删除工地项目信息
     * 
     * @param siteId 工地项目主键
     * @return 结果
     */
    @Override
    public int deleteProjectSiteBySiteId(Long siteId)
    {
        return projectSiteMapper.deleteProjectSiteBySiteId(siteId);
    }


}
