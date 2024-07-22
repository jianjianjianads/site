package com.ruoyi.project.service;

import java.util.List;
import com.ruoyi.project.domain.ProjectElevator;

/**
 * 工程电梯管理Service接口
 * 
 * @author SWE20054
 * @date 2024-01-05
 */
public interface IProjectElevatorService 
{
    /**
     * 查询工程电梯管理
     * 
     * @param elevatorId 工程电梯管理主键
     * @return 工程电梯管理
     */
    public ProjectElevator selectProjectElevatorByElevatorId(Long elevatorId);

    /**
     * 查询工程电梯管理列表
     * 
     * @param projectElevator 工程电梯管理
     * @return 工程电梯管理集合
     */
    public List<ProjectElevator> selectProjectElevatorList(ProjectElevator projectElevator);

    /**
     * 新增工程电梯管理
     * 
     * @param projectElevator 工程电梯管理
     * @return 结果
     */
    public int insertProjectElevator(ProjectElevator projectElevator);

    /**
     * 修改工程电梯管理
     * 
     * @param projectElevator 工程电梯管理
     * @return 结果
     */
    public int updateProjectElevator(ProjectElevator projectElevator);

    /**
     * 批量删除工程电梯管理
     * 
     * @param elevatorIds 需要删除的工程电梯管理主键集合
     * @return 结果
     */
    public int deleteProjectElevatorByElevatorIds(Long[] elevatorIds);

    /**
     * 删除工程电梯管理信息
     * 
     * @param elevatorId 工程电梯管理主键
     * @return 结果
     */
    public int deleteProjectElevatorByElevatorId(Long elevatorId);
}
