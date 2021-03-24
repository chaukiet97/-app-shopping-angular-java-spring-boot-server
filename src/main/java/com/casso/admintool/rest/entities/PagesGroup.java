package com.casso.admintool.rest.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class PagesGroup  implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Date created_time;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PagesGroup() {
    }

    public PagesGroup(Integer id, String name, Date created_time) {
        this.id = id;
        this.name = name;
        this.created_time = created_time;
    }
}