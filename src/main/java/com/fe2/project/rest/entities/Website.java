package com.fe2.project.rest.entities;

import java.io.Serializable;
import javax.persistence.*;
@Entity
public class Website implements Serializable{
        /**
    *
    */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String logo;
    private String shortcut;
    private String title;
    private String description;
    public Website(Integer id, String logo, String shortcut, String title, String description) {
        this.id = id;
        this.logo = logo;
        this.shortcut = shortcut;
        this.title = title;
        this.description = description;
    }
    public Website() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getShortcut() {
        return shortcut;
    }
    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }
    public String getLogo() {
        return logo;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}
