package com.ruoyi.project.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工地环境情况管理对象 site_weather
 * 
 * @author SWE20054林坤城
 * @date 2024-04-15
 */
public class SiteWeather extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工地id */
    @Excel(name = "工地id")
    private Long siteId;

    /** 风速 */
    @Excel(name = "风速")
    private BigDecimal windSpeed;

    /** 温度 */
    @Excel(name = "温度")
    private BigDecimal temperature;

    /** pm2.5浓度 */
    @Excel(name = "pm2.5浓度")
    private BigDecimal pm25;

    /** pm10浓度 */
    @Excel(name = "pm10浓度")
    private BigDecimal pm10;

    /** 噪音分贝 */
    @Excel(name = "噪音分贝")
    private BigDecimal noise;

    /** 湿度 */
    @Excel(name = "湿度")
    private BigDecimal humidity;

    /** 日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date weatherDate;

    public void setSiteId(Long siteId) 
    {
        this.siteId = siteId;
    }

    public Long getSiteId() 
    {
        return siteId;
    }
    public void setWindSpeed(BigDecimal windSpeed) 
    {
        this.windSpeed = windSpeed;
    }

    public BigDecimal getWindSpeed() 
    {
        return windSpeed;
    }
    public void setTemperature(BigDecimal temperature) 
    {
        this.temperature = temperature;
    }

    public BigDecimal getTemperature() 
    {
        return temperature;
    }
    public void setPm25(BigDecimal pm25) 
    {
        this.pm25 = pm25;
    }

    public BigDecimal getPm25() 
    {
        return pm25;
    }
    public void setPm10(BigDecimal pm10) 
    {
        this.pm10 = pm10;
    }

    public BigDecimal getPm10() 
    {
        return pm10;
    }
    public void setNoise(BigDecimal noise) 
    {
        this.noise = noise;
    }

    public BigDecimal getNoise() 
    {
        return noise;
    }
    public void setHumidity(BigDecimal humidity) 
    {
        this.humidity = humidity;
    }

    public BigDecimal getHumidity() 
    {
        return humidity;
    }
    public void setWeatherDate(Date weatherDate) 
    {
        this.weatherDate = weatherDate;
    }

    public Date getWeatherDate() 
    {
        return weatherDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("siteId", getSiteId())
            .append("windSpeed", getWindSpeed())
            .append("temperature", getTemperature())
            .append("pm25", getPm25())
            .append("pm10", getPm10())
            .append("noise", getNoise())
            .append("humidity", getHumidity())
            .append("weatherDate", getWeatherDate())
            .toString();
    }
}
