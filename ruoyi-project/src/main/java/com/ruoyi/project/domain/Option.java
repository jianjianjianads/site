package com.ruoyi.project.domain;

import com.ruoyi.common.annotation.Excel;

import java.math.BigDecimal;

public class Option {
    @Excel(name = "返回工地名字")
    private String siteName;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Long getTowerId() {
        return towerId;
    }

    public void setTowerId(Long towerId) {
        this.towerId = towerId;
    }

    @Excel(name = "塔机id")
    private Long towerId;

}
