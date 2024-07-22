package com.ruoyi.project.mapper;

import java.util.List;
import com.ruoyi.project.domain.ElevatorFactory;

/**
 * 电梯生产厂家管理Mapper接口
 * 
 * @author SWE20054
 * @date 2024-01-06
 */
public interface ElevatorFactoryMapper 
{
    /**
     * 查询电梯生产厂家管理
     * 
     * @param factoryId 电梯生产厂家管理主键
     * @return 电梯生产厂家管理
     */
    public ElevatorFactory selectElevatorFactoryByFactoryId(Long factoryId);

    /**
     * 查询电梯生产厂家管理列表
     * 
     * @param elevatorFactory 电梯生产厂家管理
     * @return 电梯生产厂家管理集合
     */
    public List<ElevatorFactory> selectElevatorFactoryList(ElevatorFactory elevatorFactory);

    /**
     * 新增电梯生产厂家管理
     * 
     * @param elevatorFactory 电梯生产厂家管理
     * @return 结果
     */
    public int insertElevatorFactory(ElevatorFactory elevatorFactory);

    /**
     * 修改电梯生产厂家管理
     * 
     * @param elevatorFactory 电梯生产厂家管理
     * @return 结果
     */
    public int updateElevatorFactory(ElevatorFactory elevatorFactory);

    /**
     * 删除电梯生产厂家管理
     * 
     * @param factoryId 电梯生产厂家管理主键
     * @return 结果
     */
    public int deleteElevatorFactoryByFactoryId(Long factoryId);

    /**
     * 批量删除电梯生产厂家管理
     * 
     * @param factoryIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteElevatorFactoryByFactoryIds(Long[] factoryIds);
}
