package com.ruoyi.project.mapper;

import java.util.List;

import com.ruoyi.project.domain.Option;
import com.ruoyi.project.domain.ProjectTower;

/**
 * 塔机管理Mapper接口
 * 
 * @author SWE20054æå¤å
 * @date 2024-01-08
 */
public interface ProjectTowerMapper 
{
    /**
     * 查询塔机管理
     * 
     * @param towerId 塔机管理主键
     * @return 塔机管理
     */
    public ProjectTower selectProjectTowerByTowerId(Long towerId);

    /**
     * 查询塔机管理列表
     * 
     * @param projectTower 塔机管理
     * @return 塔机管理集合
     */
    public List<ProjectTower> selectProjectTowerList(ProjectTower projectTower);

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
     * 删除塔机管理
     * 
     * @param towerId 塔机管理主键
     * @return 结果
     */
    public int deleteProjectTowerByTowerId(Long towerId);

    /**
     * 批量删除塔机管理
     * 
     * @param towerIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectTowerByTowerIds(Long[] towerIds);

    //3.24新增 获取塔机分布数据
    List<ProjectTower> selectProjectTowerDistributionList(ProjectTower projectTower);
    //3.24新增 获取塔机状态数据
    List<ProjectTower> selectProjectTowerStatusList(ProjectTower projectTower);
    //3.25新增 获取塔机制造商数据
    List<ProjectTower> selectProjectTowerContractorsList(ProjectTower projectTower);

    List<Option> selectProjectTowerOptionsList();

    List<ProjectTower> selectProjectTowerBySiteId(Long siteId);
}
