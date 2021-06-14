package com.fe2.project.rest.entities;

import java.io.Serializable;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Banner implements Serializable {
        /**
    *
    */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String link;
    private String images;
    private Integer type;
    private Integer status;
    private String description;
    public Banner(Integer id, String name, String link, String images, Integer type, Integer status, Date create_time, String description) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.images = images;
        this.type = type;
        this.status = status;
        this.create_time = create_time;
        this.description =description;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    private Date create_time;
    public Banner() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public String getImages() {
        return images;
    }
    public void setImages(String images) {
        this.images = images;
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
