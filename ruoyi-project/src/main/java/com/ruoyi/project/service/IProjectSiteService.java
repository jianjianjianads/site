package com.ruoyi.project.service;

import java.util.List;

import com.ruoyi.project.domain.Option;
import com.ruoyi.project.domain.ProjectSite;
import com.ruoyi.project.domain.Result;

/**
 * 工地项目Service接口
 * 
 * @author SWE20054
 * @date 2024-01-07
 */
public interface IProjectSiteService 
{
    /**
     * 查询工地项目
     * 
     * @param siteId 工地项目主键
     * @return 工地项目
     */
    public ProjectSite selectProjectSiteBySiteId(Long siteId);

    /**
     * 查询工地项目列表
     * 
     * @param projectSite 工地项目
     * @return 工地项目集合
     */
    public List<ProjectSite> selectProjectSiteList(ProjectSite projectSite);
//3.23修改
    /**
     查询工地项目类型及其数量列表
     *
     * @param projectSite 工地项目
     * @return 工地项目集合
     */
    public List<ProjectSite> selectProjectSiteTypeList(ProjectSite projectSite);
    //
    //3.24修改
    /**
     查询工地项目状态及其数量列表
     *
     * @param projectSite 工地项目
     * @return 工地项目集合
     */
    public List<ProjectSite> selectProjectSiteStatusList(ProjectSite projectSite);
    //3.26新增
    /**
     查询工地项目进度top列表
     *
     * @param projectSite 工地项目
     * @return 工地项目集合
     */
    List<ProjectSite> selectProjectSiteProgressList(ProjectSite projectSite);

    List<ProjectSite> selectProjectSiteOptionsList();

    //
    /**
     * 新增工地项目
     * 
     * @param projectSite 工地项目
     * @return 结果
     */
    public int insertProjectSite(ProjectSite projectSite);

    /**
     * 修改工地项目
     * 
     * @param projectSite 工地项目
     * @return 结果
     */
    public int updateProjectSite(ProjectSite projectSite);

    /**
     * 批量删除工地项目
     * 
     * @param siteIds 需要删除的工地项目主键集合
     * @return 结果
     */
    public int deleteProjectSiteBySiteIds(Long[] siteIds);

    /**
     * 删除工地项目信息
     * 
     * @param siteId 工地项目主键
     * @return 结果
     */
    public int deleteProjectSiteBySiteId(Long siteId);

    List<Result> selectProjectSiteBudgetList();


}
