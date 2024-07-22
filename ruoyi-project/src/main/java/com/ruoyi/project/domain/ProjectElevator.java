package com.ruoyi.project.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工程电梯管理对象 project_elevator
 * 
 * @author SWE20054
 * @date 2024-01-05
 */
public class ProjectElevator extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 电梯ID */
    @Excel(name = "电梯ID")
    private Long elevatorId;

    /** 电梯型号 */
    @Excel(name = "电梯型号")
    private String elevatorModel;

    /** 电梯速度(M/S) */
    @Excel(name = "电梯速度(M/S)")
    private BigDecimal elevatorSpeed;

    /** 最大承载重量(KG) */
    @Excel(name = "最大承载重量(KG)")
    private BigDecimal maxLoad;

    /** 制造商 */
    @Excel(name = "制造商")
    private String factory_id;

    /** 购买日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "购买日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date purchaseDate;

    /** 使用情况记录 */
    @Excel(name = "使用情况记录")
    private String usageLog;

    /** 位置 */
    @Excel(name = "位置")
    private String location;

    /** 操作人员 */
    @Excel(name = "操作人员")
    private String operate;

    /** 所属工地 */
    @Excel(name = "所属工地")
    private String siteId;

    /** 当前状态 */
    @Excel(name = "当前状态")
    private String status;
    //电梯厂家对象
    private  ElevatorFactory elevatorFactory;
    //    工地项目对象
    private ProjectSite projectSite;

    public ProjectSite getProjectSite() {
        return projectSite;
    }

    public void setProjectSite(ProjectSite projectSite) {
        this.projectSite = projectSite;
    }

    public ElevatorFactory getElevatorFactory() {
        return elevatorFactory;
    }

    public void setElevatorFactory(ElevatorFactory elevatorFactory) {
        this.elevatorFactory = elevatorFactory;
    }

    public void setElevatorId(Long elevatorId)
    {
        this.elevatorId = elevatorId;
    }

    public Long getElevatorId() 
    {
        return elevatorId;
    }
    public void setElevatorModel(String elevatorModel) 
    {
        this.elevatorModel = elevatorModel;
    }

    public String getElevatorModel() 
    {
        return elevatorModel;
    }
    public void setElevatorSpeed(BigDecimal elevatorSpeed) 
    {
        this.elevatorSpeed = elevatorSpeed;
    }

    public BigDecimal getElevatorSpeed() 
    {
        return elevatorSpeed;
    }
    public void setMaxLoad(BigDecimal maxLoad) 
    {
        this.maxLoad = maxLoad;
    }

    public BigDecimal getMaxLoad() 
    {
        return maxLoad;
    }
    public void setfactory_id(String factory_id) 
    {
        this.factory_id = factory_id;
    }

    public String getfactory_id() 
    {
        return factory_id;
    }

    public void setPurchaseDate(Date purchaseDate) 
    {
        this.purchaseDate = purchaseDate;
    }

    public Date getPurchaseDate() 
    {
        return purchaseDate;
    }
    public void setUsageLog(String usageLog) 
    {
        this.usageLog = usageLog;
    }

    public String getUsageLog() 
    {
        return usageLog;
    }
    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }
    public void setOperate(String operate) 
    {
        this.operate = operate;
    }

    public String getOperate() 
    {
        return operate;
    }
    public void setSiteId(String siteId) 
    {
        this.siteId = siteId;
    }

    public String getSiteId() 
    {
        return siteId;
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
            .append("elevatorId", getElevatorId())
            .append("elevatorModel", getElevatorModel())
            .append("elevatorSpeed", getElevatorSpeed())
            .append("maxLoad", getMaxLoad())
            .append("factory_id", getfactory_id())
            .append("purchaseDate", getPurchaseDate())
            .append("usageLog", getUsageLog())
            .append("location", getLocation())
            .append("operate", getOperate())
            .append("siteId", getSiteId())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("projectSite", getProjectSite())
                .append("elevatorFactory", getElevatorFactory())
                .toString();
    }
}
