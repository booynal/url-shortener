package com.ziv.urlshotener.dto;


import java.sql.Date;

/**
 * @author ziv
 */
public class UrlMapping {

    private long id;
    private String longUrl;
    private Date createTime;
    private Date expireTime;

    public UrlMapping() {
    }

    public UrlMapping(String longUrl, Date createTime, Date expireTime) {
        this.longUrl = longUrl;
        this.createTime = createTime;
        this.expireTime = expireTime;
    }

    public UrlMapping(long id, String longUrl, Date createTime, Date expireTime) {
        this.id = id;
        this.longUrl = longUrl;
        this.createTime = createTime;
        this.expireTime = expireTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        return "UrlMapping{" +
                "id=" + id +
                ", longUrl='" + longUrl + '\'' +
                ", createTime=" + createTime +
                ", expireTime=" + expireTime +
                '}';
    }
}
