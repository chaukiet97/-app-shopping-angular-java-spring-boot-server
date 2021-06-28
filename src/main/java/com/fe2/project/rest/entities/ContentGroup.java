package com.fe2.project.rest.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class ContentGroup implements Serializable {
    /**
    *
    */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String link;
    private Date create_time;
    public ContentGroup(Integer id, String name, String link, Date create_time) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.create_time = create_time;
    }
    public ContentGroup() {
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
