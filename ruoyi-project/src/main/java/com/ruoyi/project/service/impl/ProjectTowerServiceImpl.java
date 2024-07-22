package com.ruoyi.project.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.domain.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.mapper.ProjectTowerMapper;
import com.ruoyi.project.domain.ProjectTower;
import com.ruoyi.project.service.IProjectTowerService;

/**
 * 塔机管理Service业务层处理
 * 
 * @author SWE20054æå¤å
 * @date 2024-01-08
 */
@Service
public class ProjectTowerServiceImpl implements IProjectTowerService 
{
    @Autowired
    private ProjectTowerMapper projectTowerMapper;

    /**
     * 查询塔机管理
     * 
     * @param towerId 塔机管理主键
     * @return 塔机管理
     */
    @Override
    public ProjectTower selectProjectTowerByTowerId(Long towerId)
    {
        return projectTowerMapper.selectProjectTowerByTowerId(towerId);
    }

    @Override
    public List<ProjectTower> selectProjectTowerBySiteId(Long siteId) {
        return projectTowerMapper.selectProjectTowerBySiteId(siteId);
    }

//    @Override
//    public List<ProjectTower> selectProjectTowerBySiteId(Long siteId) {
//        return projectTowerMapper.selectProjectTowerBySiteId(siteId);
//    }

    /**
     * 查询塔机管理列表
     * 
     * @param projectTower 塔机管理
     * @return 塔机管理
     */
    @Override
    public List<ProjectTower> selectProjectTowerList(ProjectTower projectTower)
    {
        return projectTowerMapper.selectProjectTowerList(projectTower);
    }
//3.24新增
    @Override
    public List<ProjectTower> selectProjectTowerDistributionList(ProjectTower projectTower) {
        return projectTowerMapper.selectProjectTowerDistributionList(projectTower);
    }

    @Override
    public List<ProjectTower> selectProjectTowerStatusList(ProjectTower projectTower) {
        return projectTowerMapper.selectProjectTowerStatusList(projectTower);
    }

    @Override
    public List<ProjectTower> selectProjectTowerContractorsList(ProjectTower projectTower) {
        return projectTowerMapper.selectProjectTowerContractorsList(projectTower);
    }
//3.27新增
    /**
     * 查询塔机选项数据
     * @return 查询塔机选项数据集合
     */
    @Override
    public List<Option> selectProjectTowerOptionsList() {
        return projectTowerMapper.selectProjectTowerOptionsList();
    }

    /**
     * 新增塔机管理
     * 
     * @param projectTower 塔机管理
     * @return 结果
     */
    @Override
    public int insertProjectTower(ProjectTower projectTower)
    {
        projectTower.setCreateTime(DateUtils.getNowDate());
        return projectTowerMapper.insertProjectTower(projectTower);
    }

    /**
     * 修改塔机管理
     * 
     * @param projectTower 塔机管理
     * @return 结果
     */
    @Override
    public int updateProjectTower(ProjectTower projectTower)
    {
        projectTower.setUpdateTime(DateUtils.getNowDate());
        return projectTowerMapper.updateProjectTower(projectTower);
    }

    /**
     * 批量删除塔机管理
     * 
     * @param towerIds 需要删除的塔机管理主键
     * @return 结果
     */
    @Override
    public int deleteProjectTowerByTowerIds(Long[] towerIds)
    {
        return projectTowerMapper.deleteProjectTowerByTowerIds(towerIds);
    }

    /**
     * 删除塔机管理信息
     * 
     * @param towerId 塔机管理主键
     * @return 结果
     */
    @Override
    public int deleteProjectTowerByTowerId(Long towerId)
    {
        return projectTowerMapper.deleteProjectTowerByTowerId(towerId);
    }

}
