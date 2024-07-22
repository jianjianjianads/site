package com.ruoyi.project.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.mapper.TowerFactoryMapper;
import com.ruoyi.project.domain.TowerFactory;
import com.ruoyi.project.service.ITowerFactoryService;

/**
 * 塔机生产厂家管理Service业务层处理
 * 
 * @author SWE20054æå¤å
 * @date 2024-01-07
 */
@Service
public class TowerFactoryServiceImpl implements ITowerFactoryService 
{
    @Autowired
    private TowerFactoryMapper towerFactoryMapper;

    /**
     * 查询塔机生产厂家管理
     * 
     * @param factoryId 塔机生产厂家管理主键
     * @return 塔机生产厂家管理
     */
    @Override
    public TowerFactory selectTowerFactoryByFactoryId(Long factoryId)
    {
        return towerFactoryMapper.selectTowerFactoryByFactoryId(factoryId);
    }

    /**
     * 查询塔机生产厂家管理列表
     * 
     * @param towerFactory 塔机生产厂家管理
     * @return 塔机生产厂家管理
     */
    @Override
    public List<TowerFactory> selectTowerFactoryList(TowerFactory towerFactory)
    {
        return towerFactoryMapper.selectTowerFactoryList(towerFactory);
    }

    /**
     * 新增塔机生产厂家管理
     * 
     * @param towerFactory 塔机生产厂家管理
     * @return 结果
     */
    @Override
    public int insertTowerFactory(TowerFactory towerFactory)
    {
        towerFactory.setCreateTime(DateUtils.getNowDate());
        return towerFactoryMapper.insertTowerFactory(towerFactory);
    }

    /**
     * 修改塔机生产厂家管理
     * 
     * @param towerFactory 塔机生产厂家管理
     * @return 结果
     */
    @Override
    public int updateTowerFactory(TowerFactory towerFactory)
    {
        towerFactory.setUpdateTime(DateUtils.getNowDate());
        return towerFactoryMapper.updateTowerFactory(towerFactory);
    }

    /**
     * 批量删除塔机生产厂家管理
     * 
     * @param factoryIds 需要删除的塔机生产厂家管理主键
     * @return 结果
     */
    @Override
    public int deleteTowerFactoryByFactoryIds(Long[] factoryIds)
    {
        return towerFactoryMapper.deleteTowerFactoryByFactoryIds(factoryIds);
    }

    /**
     * 删除塔机生产厂家管理信息
     * 
     * @param factoryId 塔机生产厂家管理主键
     * @return 结果
     */
    @Override
    public int deleteTowerFactoryByFactoryId(Long factoryId)
    {
        return towerFactoryMapper.deleteTowerFactoryByFactoryId(factoryId);
    }
}
