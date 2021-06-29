package com.fe2.project.rest.repositories.DTO;

import java.util.Date;

public class ContentDTO {
    private Integer id;
    private String name;
    private String link;
    private Integer group_id;
    private String images;
    private String detail;
    private String description;
    private Integer status;
    private Date create_time;
    private String name_group;
    private String parent_link;
    public ContentDTO(Integer id, String name, String link, Integer group_id, String images, String detail,
            String description, Integer status, Date create_time,String name_group, String parent_link) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.group_id = group_id;
        this.images = images;
        this.detail = detail;
        this.description = description;
        this.status = status;
        this.create_time = create_time;
        this.name_group = name_group;
        this.parent_link = parent_link;
    }
    public String getParent_link() {
        return parent_link;
    }
    public void setParent_link(String parent_link) {
        this.parent_link = parent_link;
    }
    public String getName_group() {
        return name_group;
    }
    public void setName_group(String name_group) {
        this.name_group = name_group;
    }
    public ContentDTO() {
    }
    public Integer getId() {
        return id;
    }
    public Date getCreate_time() {
        return create_time;
    }
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public String getImages() {
        return images;
    }
    public void setImages(String images) {
        this.images = images;
    }
    public Integer getGroup_id() {
        return group_id;
    }
    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}
