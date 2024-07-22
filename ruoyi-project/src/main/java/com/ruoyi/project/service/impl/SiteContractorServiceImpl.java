package com.ruoyi.project.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.mapper.SiteContractorMapper;
import com.ruoyi.project.domain.SiteContractor;
import com.ruoyi.project.service.ISiteContractorService;

/**
 * 工地承包商管理Service业务层处理
 * 
 * @author SWE20054
 * @date 2024-01-07
 */
@Service
public class SiteContractorServiceImpl implements ISiteContractorService 
{
    @Autowired
    private SiteContractorMapper siteContractorMapper;

    /**
     * 查询工地承包商管理
     * 
     * @param contractorId 工地承包商管理主键
     * @return 工地承包商管理
     */
    @Override
    public SiteContractor selectSiteContractorByContractorId(Long contractorId)
    {
        return siteContractorMapper.selectSiteContractorByContractorId(contractorId);
    }

    /**
     * 查询工地承包商管理列表
     * 
     * @param siteContractor 工地承包商管理
     * @return 工地承包商管理
     */
    @Override
    public List<SiteContractor> selectSiteContractorList(SiteContractor siteContractor)
    {
        return siteContractorMapper.selectSiteContractorList(siteContractor);
    }

    /**
     * 新增工地承包商管理
     * 
     * @param siteContractor 工地承包商管理
     * @return 结果
     */
    @Override
    public int insertSiteContractor(SiteContractor siteContractor)
    {
        siteContractor.setCreateTime(DateUtils.getNowDate());
        return siteContractorMapper.insertSiteContractor(siteContractor);
    }

    /**
     * 修改工地承包商管理
     * 
     * @param siteContractor 工地承包商管理
     * @return 结果
     */
    @Override
    public int updateSiteContractor(SiteContractor siteContractor)
    {
        siteContractor.setUpdateTime(DateUtils.getNowDate());
        return siteContractorMapper.updateSiteContractor(siteContractor);
    }

    /**
     * 批量删除工地承包商管理
     * 
     * @param contractorIds 需要删除的工地承包商管理主键
     * @return 结果
     */
    @Override
    public int deleteSiteContractorByContractorIds(Long[] contractorIds)
    {
        return siteContractorMapper.deleteSiteContractorByContractorIds(contractorIds);
    }

    /**
     * 删除工地承包商管理信息
     * 
     * @param contractorId 工地承包商管理主键
     * @return 结果
     */
    @Override
    public int deleteSiteContractorByContractorId(Long contractorId)
    {
        return siteContractorMapper.deleteSiteContractorByContractorId(contractorId);
    }
}
