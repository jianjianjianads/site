package com.ruoyi.project.service;

import java.util.List;

import com.ruoyi.project.domain.Option;
import com.ruoyi.project.domain.ProjectTower;

/**
 * 塔机管理Service接口
 * 
 * @author SWE20054æå¤å
 * @date 2024-01-08
 */
public interface IProjectTowerService 
{
    /**
     * 查询塔机管理
     * 
     * @param towerId 塔机管理主键
     * @return 塔机管理
     */
    public ProjectTower selectProjectTowerByTowerId(Long towerId);
    public List<ProjectTower> selectProjectTowerBySiteId(Long siteId);

    /**
     * 查询塔机管理列表
     * 
     * @param projectTower 塔机管理
     * @return 塔机管理集合
     */
    public List<ProjectTower> selectProjectTowerList(ProjectTower projectTower);
    //3.24新增
    /**
     * 查询塔机分布列表
     *
     * @param projectTower 塔机管理
     * @return 塔机管理集合
     */
    public List<ProjectTower> selectProjectTowerDistributionList(ProjectTower projectTower);
    //3.24新增
    /**
     * 查询塔机状态列表
     *
     * @param projectTower 塔机管理
     * @return 塔机管理集合
     */
    public List<ProjectTower> selectProjectTowerStatusList(ProjectTower projectTower);
    //
    //3.25新增
    /**
     * 查询塔机制造商列表
     *
     * @param projectTower 塔机管理
     * @return 塔机制造商集合
     */
    List<ProjectTower> selectProjectTowerContractorsList(ProjectTower projectTower);

    //3.27新增
    /**
     * 查询塔机选项数据
     *
     * @return 查询塔机选项数据集合
     */
    List<Option> selectProjectTowerOptionsList();
    /**
     * 新增塔机管理
     * 
     * @param projectTower 塔机管理
     * @return 结果
     */
    public int insertProjectTower(ProjectTower projectTower);

    /**
     * 修改塔机管理
     * 
     * @param projectTower 塔机管理
     * @return 结果
     */
    public int updateProjectTower(ProjectTower projectTower);

    /**
     * 批量删除塔机管理
     * 
     * @param towerIds 需要删除的塔机管理主键集合
     * @return 结果
     */
    public int deleteProjectTowerByTowerIds(Long[] towerIds);

    /**
     * 删除塔机管理信息
     * 
     * @param towerId 塔机管理主键
     * @return 结果
     */
    public int deleteProjectTowerByTowerId(Long towerId);



}
