package com.ruoyi.project.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工地项目对象 project_site
 * 
 * @author SWE20054
 * @date 2024-01-07
 */
public class ProjectSite extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工地项目ID */
    @Excel(name = "工地项目ID")
    private Long siteId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String siteName;

    /** 项目描述 */
    @Excel(name = "项目描述")
    private String description;

    /** 项目进度% */
    @Excel(name = "项目进度%")
    private Long progress;

    /** 承包商 */
    @Excel(name = "承包商")
    private Long contractorId;

    /** 项目经理 */
    @Excel(name = "项目经理")
    private Long manager;

    /** 开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 项目类型 */
    @Excel(name = "项目类型")
    private String type;

    /** 项目预算 */
    @Excel(name = "项目预算")
    private BigDecimal budget;

    /** 项目地点 */
    @Excel(name = "项目地点")
    private String location;

    /** 项目当前状态 */
    @Excel(name = "项目当前状态")
    private String status;

    //承包商对象
    private SiteContractor siteContractor;

    public SiteContractor getSiteContractor() {
        return siteContractor;
    }

    public void setSiteContractor(SiteContractor siteContractor) {
        this.siteContractor = siteContractor;
    }

    public void setSiteId(Long siteId)
    {
        this.siteId = siteId;
    }

    public Long getSiteId() 
    {
        return siteId;
    }
    public void setSiteName(String siteName) 
    {
        this.siteName = siteName;
    }

    public String getSiteName() 
    {
        return siteName;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setProgress(Long progress) 
    {
        this.progress = progress;
    }

    public Long getProgress() 
    {
        return progress;
    }
    public void setContractorId(Long contractorId) 
    {
        this.contractorId = contractorId;
    }

    public Long getContractorId() 
    {
        return contractorId;
    }
    public void setManager(Long manager) 
    {
        this.manager = manager;
    }

    public Long getManager() 
    {
        return manager;
    }
    public void setStartDate(Date startDate) 
    {
        this.startDate = startDate;
    }

    public Date getStartDate() 
    {
        return startDate;
    }
    public void setEndDate(Date endDate) 
    {
        this.endDate = endDate;
    }

    public Date getEndDate() 
    {
        return endDate;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setBudget(BigDecimal budget) 
    {
        this.budget = budget;
    }

    public BigDecimal getBudget() 
    {
        return budget;
    }
    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
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
            .append("siteId", getSiteId())
            .append("siteName", getSiteName())
            .append("description", getDescription())
            .append("progress", getProgress())
            .append("contractorId", getContractorId())
            .append("manager", getManager())
            .append("startDate", getStartDate())
            .append("endDate", getEndDate())
            .append("type", getType())
            .append("budget", getBudget())
            .append("location", getLocation())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
                .append("siteContractor", getSiteContractor())
            .toString();
    }
}
