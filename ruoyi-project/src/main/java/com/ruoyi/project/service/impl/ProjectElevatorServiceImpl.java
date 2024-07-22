package com.ruoyi.project.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.mapper.ProjectElevatorMapper;
import com.ruoyi.project.domain.ProjectElevator;
import com.ruoyi.project.service.IProjectElevatorService;

/**
 * 工程电梯管理Service业务层处理
 * 
 * @author SWE20054
 * @date 2024-01-05
 */
@Service
public class ProjectElevatorServiceImpl implements IProjectElevatorService 
{
    @Autowired
    private ProjectElevatorMapper projectElevatorMapper;

    /**
     * 查询工程电梯管理
     * 
     * @param elevatorId 工程电梯管理主键
     * @return 工程电梯管理
     */
    @Override
    public ProjectElevator selectProjectElevatorByElevatorId(Long elevatorId)
    {
        return projectElevatorMapper.selectProjectElevatorByElevatorId(elevatorId);
    }

    /**
     * 查询工程电梯管理列表
     * 
     * @param projectElevator 工程电梯管理
     * @return 工程电梯管理
     */
    @Override
    public List<ProjectElevator> selectProjectElevatorList(ProjectElevator projectElevator)
    {
        return projectElevatorMapper.selectProjectElevatorList(projectElevator);
    }

    /**
     * 新增工程电梯管理
     * 
     * @param projectElevator 工程电梯管理
     * @return 结果
     */
    @Override
    public int insertProjectElevator(ProjectElevator projectElevator)
    {
        projectElevator.setCreateTime(DateUtils.getNowDate());
        return projectElevatorMapper.insertProjectElevator(projectElevator);
    }

    /**
     * 修改工程电梯管理
     * 
     * @param projectElevator 工程电梯管理
     * @return 结果
     */
    @Override
    public int updateProjectElevator(ProjectElevator projectElevator)
    {
        projectElevator.setUpdateTime(DateUtils.getNowDate());
        return projectElevatorMapper.updateProjectElevator(projectElevator);
    }

    /**
     * 批量删除工程电梯管理
     * 
     * @param elevatorIds 需要删除的工程电梯管理主键
     * @return 结果
     */
    @Override
    public int deleteProjectElevatorByElevatorIds(Long[] elevatorIds)
    {
        return projectElevatorMapper.deleteProjectElevatorByElevatorIds(elevatorIds);
    }

    /**
     * 删除工程电梯管理信息
     * 
     * @param elevatorId 工程电梯管理主键
     * @return 结果
     */
    @Override
    public int deleteProjectElevatorByElevatorId(Long elevatorId)
    {
        return projectElevatorMapper.deleteProjectElevatorByElevatorId(elevatorId);
    }
}
