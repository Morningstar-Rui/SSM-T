package com.team.house.utils;

public class SearchTerm extends PageParam {
    private String title;
    private Integer did;
    private Integer sid;
    private Integer tid;
    private Long minPrice;
    private Long maxPrice;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Long getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Long minPrice) {
        this.minPrice = minPrice;
    }

    public Long getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Long maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public String toString() {
        return "SearchTerm{" +
                "title='" + title + '\'' +
                ", did=" + did +
                ", sid=" + sid +
                ", tid=" + tid +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                '}';
    }
}
