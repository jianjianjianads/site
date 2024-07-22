package com.ruoyi.project.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工地承包商管理对象 site_contractor
 * 
 * @author SWE20054
 * @date 2024-01-07
 */
public class SiteContractor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 承包商ID */
    @Excel(name = "承包商ID")
    private Long contractorId;

    /** 承包商名称 */
    @Excel(name = "承包商名称")
    private String contractorName;

    /** 承包商编码 */
    @Excel(name = "承包商编码")
    private String contractorCode;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contact;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 关键字 */
    @Excel(name = "关键字")
    private String keyword;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setContractorId(Long contractorId) 
    {
        this.contractorId = contractorId;
    }

    public Long getContractorId() 
    {
        return contractorId;
    }
    public void setContractorName(String contractorName) 
    {
        this.contractorName = contractorName;
    }

    public String getContractorName() 
    {
        return contractorName;
    }
    public void setContractorCode(String contractorCode) 
    {
        this.contractorCode = contractorCode;
    }

    public String getContractorCode() 
    {
        return contractorCode;
    }
    public void setContact(String contact) 
    {
        this.contact = contact;
    }

    public String getContact() 
    {
        return contact;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setKeyword(String keyword) 
    {
        this.keyword = keyword;
    }

    public String getKeyword() 
    {
        return keyword;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("contractorId", getContractorId())
            .append("contractorName", getContractorName())
            .append("contractorCode", getContractorCode())
            .append("contact", getContact())
            .append("phone", getPhone())
            .append("keyword", getKeyword())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
