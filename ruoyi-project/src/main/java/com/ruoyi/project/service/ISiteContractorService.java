package com.ruoyi.project.service;

import java.util.List;
import com.ruoyi.project.domain.SiteContractor;

/**
 * 工地承包商管理Service接口
 * 
 * @author SWE20054
 * @date 2024-01-07
 */
public interface ISiteContractorService 
{
    /**
     * 查询工地承包商管理
     * 
     * @param contractorId 工地承包商管理主键
     * @return 工地承包商管理
     */
    public SiteContractor selectSiteContractorByContractorId(Long contractorId);

    /**
     * 查询工地承包商管理列表
     * 
     * @param siteContractor 工地承包商管理
     * @return 工地承包商管理集合
     */
    public List<SiteContractor> selectSiteContractorList(SiteContractor siteContractor);

    /**
     * 新增工地承包商管理
     * 
     * @param siteContractor 工地承包商管理
     * @return 结果
     */
    public int insertSiteContractor(SiteContractor siteContractor);

    /**
     * 修改工地承包商管理
     * 
     * @param siteContractor 工地承包商管理
     * @return 结果
     */
    public int updateSiteContractor(SiteContractor siteContractor);

    /**
     * 批量删除工地承包商管理
     * 
     * @param contractorIds 需要删除的工地承包商管理主键集合
     * @return 结果
     */
    public int deleteSiteContractorByContractorIds(Long[] contractorIds);

    /**
     * 删除工地承包商管理信息
     * 
     * @param contractorId 工地承包商管理主键
     * @return 结果
     */
    public int deleteSiteContractorByContractorId(Long contractorId);
}
