package com.yggdrasil.paper.entity;

import java.util.Date;
import java.util.List;

/*
* 网页传入的reqBody封装成的信息对象
* */
public class RandomInfo {
    private List<String> stuList;
    private List<String> webList;
    private Date startDate;
    private Date endDate;
    private int length;

    public List<String> getStuList() {
        return stuList;
    }

    public void setStuList(List<String> stuList) {
        this.stuList = stuList;
    }

    public List<String> getWebList() {
        return webList;
    }

    public void setWebList(List<String> webList) {
        this.webList = webList;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
