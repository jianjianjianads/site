package com.ruoyi.project.mapper;

import java.util.List;
import com.ruoyi.project.domain.ProjectSite;
import com.ruoyi.project.domain.Result;

/**
 * 工地项目Mapper接口
 * 
 * @author SWE20054
 * @date 2024-01-07
 */
public interface ProjectSiteMapper 
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
     * 查询工地项目分布及其数量
     *
     * @param projectSite 工地项目
     * @return 工地项目集合
     */
    public List<ProjectSite> selectProjectSiteTypeList(ProjectSite projectSite);
    //3.24
    /**
     * 查询工地状态及其数量
     *
     * @param projectSite 工地项目
     * @return 工地项目集合
     */
    public List<ProjectSite> selectProjectSiteStatusList(ProjectSite projectSite);
    //3.26新增获取预算分布
    List<Result> selectProjectSiteBudgetList();
    //3.26新增获取项目进度top5
    List<ProjectSite> selectProjectSiteProgressList(ProjectSite projectSite);
///
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
     * 删除工地项目
     * 
     * @param siteId 工地项目主键
     * @return 结果
     */
    public int deleteProjectSiteBySiteId(Long siteId);

    /**
     * 批量删除工地项目
     * 
     * @param siteIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectSiteBySiteIds(Long[] siteIds);


    List<ProjectSite> selectProjectSiteOptionList();
}
