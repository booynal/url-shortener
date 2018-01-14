package com.ziv.urlshotener.dto;


import java.sql.Date;

/**
 * @author ziv
 */
public class UrlAnalyze {

    private long id;
    private long urlId;
    private Date clickTime;
    private String clickIp;
    private String extInfo;

    public UrlAnalyze() {
    }

    public UrlAnalyze(long id, long urlId, Date clickTime, String clickIp, String extInfo) {
        this.id = id;
        this.urlId = urlId;
        this.clickTime = clickTime;
        this.clickIp = clickIp;
        this.extInfo = extInfo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUrlId() {
        return urlId;
    }

    public void setUrlId(long urlId) {
        this.urlId = urlId;
    }

    public Date getClickTime() {
        return clickTime;
    }

    public void setClickTime(Date clickTime) {
        this.clickTime = clickTime;
    }

    public String getClickIp() {
        return clickIp;
    }

    public void setClickIp(String clickIp) {
        this.clickIp = clickIp;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    @Override
    public String toString() {
        return "UrlAnalyze{" +
                "id=" + id +
                ", urlId=" + urlId +
                ", clickTime=" + clickTime +
                ", clickIp='" + clickIp + '\'' +
                ", extInfo='" + extInfo + '\'' +
                '}';
    }
}
