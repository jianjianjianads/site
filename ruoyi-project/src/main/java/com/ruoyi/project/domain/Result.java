package com.ruoyi.project.domain;

import com.ruoyi.common.annotation.Excel;

import java.math.BigDecimal;

public class Result {
    @Excel(name = "返回类型名字")
    private String name;

    @Excel(name = "数量")
    private BigDecimal num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }
}
