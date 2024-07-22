package com.ruoyi.project.service.impl;

import java.util.List;

import com.ruoyi.project.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.mapper.ErrorTowerMapper;
import com.ruoyi.project.domain.ErrorTower;
import com.ruoyi.project.service.IErrorTowerService;

/**
 * 异常信息管理Service业务层处理
 * 
 * @author SWE20054橘子
 * @date 2024-03-31
 */
@Service
public class ErrorTowerServiceImpl implements IErrorTowerService 
{
    @Autowired
    private ErrorTowerMapper errorTowerMapper;

    /**
     * 查询异常信息管理
     * 
     * @param errorId 异常信息管理主键
     * @return 异常信息管理
     */
    @Override
    public ErrorTower selectErrorTowerByErrorId(Long errorId)
    {
        return errorTowerMapper.selectErrorTowerByErrorId(errorId);
    }

    /**
     * 查询异常信息管理列表
     * 
     * @param errorTower 异常信息管理
     * @return 异常信息管理
     */
    @Override
    public List<ErrorTower> selectErrorTowerList(ErrorTower errorTower)
    {
        return errorTowerMapper.selectErrorTowerList(errorTower);
    }

    @Override
    public List<ErrorTower> selectErrorTowerBySiteId(Long siteId) {
        return errorTowerMapper.selectErrorTowerBySiteId(siteId);
    }

    @Override
    public List<Result> selectErrorTowerAllBySiteId(Long siteId) {
        return errorTowerMapper.selectErrorTowerAllBySiteId(siteId);
    }

    /**
     * 新增异常信息管理
     * 
     * @param errorTower 异常信息管理
     * @return 结果
     */
    @Override
    public int insertErrorTower(ErrorTower errorTower)
    {
        return errorTowerMapper.insertErrorTower(errorTower);
    }

    /**
     * 修改异常信息管理
     * 
     * @param errorTower 异常信息管理
     * @return 结果
     */
    @Override
    public int updateErrorTower(ErrorTower errorTower)
    {
        return errorTowerMapper.updateErrorTower(errorTower);
    }

    /**
     * 批量删除异常信息管理
     * 
     * @param errorIds 需要删除的异常信息管理主键
     * @return 结果
     */
    @Override
    public int deleteErrorTowerByErrorIds(Long[] errorIds)
    {
        return errorTowerMapper.deleteErrorTowerByErrorIds(errorIds);
    }

    /**
     * 删除异常信息管理信息
     * 
     * @param errorId 异常信息管理主键
     * @return 结果
     */
    @Override
    public int deleteErrorTowerByErrorId(Long errorId)
    {
        return errorTowerMapper.deleteErrorTowerByErrorId(errorId);
    }
}
