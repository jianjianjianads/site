package com.ruoyi.project.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 塔机管理对象 project_tower
 * 
 * @author SWE20054æå¤å
 * @date 2024-01-08
 */
public class ProjectTower extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 塔机ID */
    @Excel(name = "塔机ID")
    private Long towerId;

    /** 塔机型号 */
    @Excel(name = "塔机型号")
    private String towerModel;

    /** 塔机高度(M) */
    @Excel(name = "塔机高度(M)")
    private BigDecimal towerHeight;

    /** 载重(KG) */
    @Excel(name = "载重(KG)")
    private BigDecimal maxLoad;

    /** 大臂角度° */
    @Excel(name = "大臂角度°")
    private BigDecimal jibAngle;

    /** 力矩 */
    @Excel(name = "力矩")
    private BigDecimal moment;

    /** 风速(M/S) */
    @Excel(name = "风速(M/S)")
    private BigDecimal windSpeed;

    /** 塔机倾斜角度 */
    @Excel(name = "塔机倾斜角度")
    private BigDecimal tiltAngle;

    /** 小车位置 */
    @Excel(name = "小车位置")
    private String cartLocation;

    /** 制造商 */
    @Excel(name = "制造商")
    private Long factoryId;

    /** 购买日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "购买日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date purchaseDate;

    /** 操作人员 */
    @Excel(name = "操作人员")
    private String operate;

    /** 所属工地 */
    @Excel(name = "所属工地")
    private String siteId;

    /** 当前状态 */
    @Excel(name = "当前状态")
    private String status;

    private TowerFactory towerFactory;
    //塔机厂家对象

    public ProjectSite getProjectSite() {
        return projectSite;
    }

    public void setProjectSite(ProjectSite projectSite) {
        this.projectSite = projectSite;
    }

    //    工地项目对象
    private ProjectSite projectSite;

    public TowerFactory getTowerFactory() {
        return towerFactory;
    }

    public void setTowerFactory(TowerFactory towerFactory) {
        this.towerFactory = towerFactory;
    }

    public void setTowerId(Long towerId)
    {
        this.towerId = towerId;
    }

    public Long getTowerId() 
    {
        return towerId;
    }
    public void setTowerModel(String towerModel) 
    {
        this.towerModel = towerModel;
    }

    public String getTowerModel() 
    {
        return towerModel;
    }
    public void setTowerHeight(BigDecimal towerHeight) 
    {
        this.towerHeight = towerHeight;
    }

    public BigDecimal getTowerHeight() 
    {
        return towerHeight;
    }
    public void setMaxLoad(BigDecimal maxLoad) 
    {
        this.maxLoad = maxLoad;
    }

    public BigDecimal getMaxLoad() 
    {
        return maxLoad;
    }
    public void setJibAngle(BigDecimal jibAngle) 
    {
        this.jibAngle = jibAngle;
    }

    public BigDecimal getJibAngle() 
    {
        return jibAngle;
    }
    public void setMoment(BigDecimal moment) 
    {
        this.moment = moment;
    }

    public BigDecimal getMoment() 
    {
        return moment;
    }
    public void setWindSpeed(BigDecimal windSpeed) 
    {
        this.windSpeed = windSpeed;
    }

    public BigDecimal getWindSpeed() 
    {
        return windSpeed;
    }
    public void setTiltAngle(BigDecimal tiltAngle) 
    {
        this.tiltAngle = tiltAngle;
    }

    public BigDecimal getTiltAngle() 
    {
        return tiltAngle;
    }
    public void setCartLocation(String cartLocation) 
    {
        this.cartLocation = cartLocation;
    }

    public String getCartLocation() 
    {
        return cartLocation;
    }
    public void setFactoryId(Long factoryId) 
    {
        this.factoryId = factoryId;
    }

    public Long getFactoryId() 
    {
        return factoryId;
    }
    public void setPurchaseDate(Date purchaseDate) 
    {
        this.purchaseDate = purchaseDate;
    }

    public Date getPurchaseDate() 
    {
        return purchaseDate;
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
            .append("towerId", getTowerId())
            .append("towerModel", getTowerModel())
            .append("towerHeight", getTowerHeight())
            .append("maxLoad", getMaxLoad())
            .append("jibAngle", getJibAngle())
            .append("moment", getMoment())
            .append("windSpeed", getWindSpeed())
            .append("tiltAngle", getTiltAngle())
            .append("cartLocation", getCartLocation())
            .append("factoryId", getFactoryId())
            .append("purchaseDate", getPurchaseDate())
            .append("operate", getOperate())
            .append("siteId", getSiteId())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("towerFactory", getTowerFactory())
                .append("projectSite", getProjectSite())
            .toString();
    }
}
