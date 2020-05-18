package cn.wujunya.space.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Storage {
    private Long id;

    private String name;

    private Long userId;

    @JsonFormat(pattern="YYYY-MM-dd",timezone="GMT+8")
    private Date created;

    @JsonFormat(pattern="YYYY-MM-dd",timezone="GMT+8")
    private Date updated;

    private String size;

    private Integer state;

    private Long parent;

    private Integer isFolder;

    private String adress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public Integer getIsFolder() {
        return isFolder;
    }

    public void setIsFolder(Integer isFolder) {
        this.isFolder = isFolder;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}