package com.ruoyi.project.service;

import java.util.List;
import com.ruoyi.project.domain.ErrorTower;

/**
 * 异常信息管理Service接口
 * 
 * @author SWE20054橘子
 * @date 2024-03-31
 */
public interface IErrorTowerService 
{
    /**
     * 查询异常信息管理
     * 
     * @param errorId 异常信息管理主键
     * @return 异常信息管理
     */
    public ErrorTower selectErrorTowerByErrorId(Long errorId);

    /**
     * 查询异常信息管理列表
     * 
     * @param errorTower 异常信息管理
     * @return 异常信息管理集合
     */
    public List<ErrorTower> selectErrorTowerList(ErrorTower errorTower);

    /**
     * 新增异常信息管理
     * 
     * @param errorTower 异常信息管理
     * @return 结果
     */
    public int insertErrorTower(ErrorTower errorTower);

    /**
     * 修改异常信息管理
     * 
     * @param errorTower 异常信息管理
     * @return 结果
     */
    public int updateErrorTower(ErrorTower errorTower);

    /**
     * 批量删除异常信息管理
     * 
     * @param errorIds 需要删除的异常信息管理主键集合
     * @return 结果
     */
    public int deleteErrorTowerByErrorIds(Long[] errorIds);

    /**
     * 删除异常信息管理信息
     * 
     * @param errorId 异常信息管理主键
     * @return 结果
     */
    public int deleteErrorTowerByErrorId(Long errorId);
}
