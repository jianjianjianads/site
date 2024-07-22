package com.ruoyi.project.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 异常信息管理对象 error_tower
 * 
 * @author SWE20054橘子
 * @date 2024-03-31
 */
public class ErrorTower extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 异常id */
    private Long errorId;

    /** 异常塔机id */
    @Excel(name = "异常塔机id")
    private Long towerId;

    /** 异常数据名 */
    @Excel(name = "异常数据名")
    private String name;

    /** 异常数据值 */
    @Excel(name = "异常数据值")
    private Long value;

    /** 异常阈值 */
    @Excel(name = "异常阈值")
    private Long max;

    public void setErrorId(Long errorId) 
    {
        this.errorId = errorId;
    }

    public Long getErrorId() 
    {
        return errorId;
    }
    public void setTowerId(Long towerId) 
    {
        this.towerId = towerId;
    }

    public Long getTowerId() 
    {
        return towerId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setValue(Long value) 
    {
        this.value = value;
    }

    public Long getValue() 
    {
        return value;
    }
    public void setMax(Long max) 
    {
        this.max = max;
    }

    public Long getMax() 
    {
        return max;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("errorId", getErrorId())
            .append("towerId", getTowerId())
            .append("name", getName())
            .append("value", getValue())
            .append("max", getMax())
            .toString();
    }
}
