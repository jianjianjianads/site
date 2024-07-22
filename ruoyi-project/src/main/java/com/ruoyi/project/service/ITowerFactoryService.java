package com.ruoyi.project.service;

import java.util.List;
import com.ruoyi.project.domain.TowerFactory;

/**
 * 塔机生产厂家管理Service接口
 * 
 * @author SWE20054æå¤å
 * @date 2024-01-07
 */
public interface ITowerFactoryService 
{
    /**
     * 查询塔机生产厂家管理
     * 
     * @param factoryId 塔机生产厂家管理主键
     * @return 塔机生产厂家管理
     */
    public TowerFactory selectTowerFactoryByFactoryId(Long factoryId);

    /**
     * 查询塔机生产厂家管理列表
     * 
     * @param towerFactory 塔机生产厂家管理
     * @return 塔机生产厂家管理集合
     */
    public List<TowerFactory> selectTowerFactoryList(TowerFactory towerFactory);

    /**
     * 新增塔机生产厂家管理
     * 
     * @param towerFactory 塔机生产厂家管理
     * @return 结果
     */
    public int insertTowerFactory(TowerFactory towerFactory);

    /**
     * 修改塔机生产厂家管理
     * 
     * @param towerFactory 塔机生产厂家管理
     * @return 结果
     */
    public int updateTowerFactory(TowerFactory towerFactory);

    /**
     * 批量删除塔机生产厂家管理
     * 
     * @param factoryIds 需要删除的塔机生产厂家管理主键集合
     * @return 结果
     */
    public int deleteTowerFactoryByFactoryIds(Long[] factoryIds);

    /**
     * 删除塔机生产厂家管理信息
     * 
     * @param factoryId 塔机生产厂家管理主键
     * @return 结果
     */
    public int deleteTowerFactoryByFactoryId(Long factoryId);
}