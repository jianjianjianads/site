package com.ruoyi.project.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.mapper.ElevatorFactoryMapper;
import com.ruoyi.project.domain.ElevatorFactory;
import com.ruoyi.project.service.IElevatorFactoryService;

/**
 * 电梯生产厂家管理Service业务层处理
 * 
 * @author SWE20054
 * @date 2024-01-06
 */
@Service
public class ElevatorFactoryServiceImpl implements IElevatorFactoryService 
{
    @Autowired
    private ElevatorFactoryMapper elevatorFactoryMapper;

    /**
     * 查询电梯生产厂家管理
     * 
     * @param factoryId 电梯生产厂家管理主键
     * @return 电梯生产厂家管理
     */
    @Override
    public ElevatorFactory selectElevatorFactoryByFactoryId(Long factoryId)
    {
        return elevatorFactoryMapper.selectElevatorFactoryByFactoryId(factoryId);
    }

    /**
     * 查询电梯生产厂家管理列表
     * 
     * @param elevatorFactory 电梯生产厂家管理
     * @return 电梯生产厂家管理
     */
    @Override
    public List<ElevatorFactory> selectElevatorFactoryList(ElevatorFactory elevatorFactory)
    {
        return elevatorFactoryMapper.selectElevatorFactoryList(elevatorFactory);
    }

    /**
     * 新增电梯生产厂家管理
     * 
     * @param elevatorFactory 电梯生产厂家管理
     * @return 结果
     */
    @Override
    public int insertElevatorFactory(ElevatorFactory elevatorFactory)
    {
        elevatorFactory.setCreateTime(DateUtils.getNowDate());
        return elevatorFactoryMapper.insertElevatorFactory(elevatorFactory);
    }

    /**
     * 修改电梯生产厂家管理
     * 
     * @param elevatorFactory 电梯生产厂家管理
     * @return 结果
     */
    @Override
    public int updateElevatorFactory(ElevatorFactory elevatorFactory)
    {
        elevatorFactory.setUpdateTime(DateUtils.getNowDate());
        return elevatorFactoryMapper.updateElevatorFactory(elevatorFactory);
    }

    /**
     * 批量删除电梯生产厂家管理
     * 
     * @param factoryIds 需要删除的电梯生产厂家管理主键
     * @return 结果
     */
    @Override
    public int deleteElevatorFactoryByFactoryIds(Long[] factoryIds)
    {
        return elevatorFactoryMapper.deleteElevatorFactoryByFactoryIds(factoryIds);
    }

    /**
     * 删除电梯生产厂家管理信息
     * 
     * @param factoryId 电梯生产厂家管理主键
     * @return 结果
     */
    @Override
    public int deleteElevatorFactoryByFactoryId(Long factoryId)
    {
        return elevatorFactoryMapper.deleteElevatorFactoryByFactoryId(factoryId);
    }
}
