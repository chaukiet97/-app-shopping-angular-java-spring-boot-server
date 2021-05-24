package com.casso.admintool.rest.entities;

import java.util.Date;
import java.io.Serializable;
import javax.persistence.*;

@Entity
public class MadeIn implements Serializable {
    /**
    *
    */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String link;
    private Date create_time;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MadeIn() {
    }

    public MadeIn(Integer id, String name, String link, Date create_time) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.create_time = create_time;
    }

}
