package com.fe2.project.rest.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Contact implements Serializable {
    /**
    *
    */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String phone;
    private String email;
    private String subject;
    private String message;
    private Date day_send;
    private String reply;
    private Date day_reply;
    private Integer maker_id;
    private Integer status;

    public Contact(Integer id, String name, String phone, String email, String subject, String message, Date day_send,
            String reply, Date day_reply, Integer maker_id, Integer status) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.day_send = day_send;
        this.reply = reply;
        this.day_reply = day_reply;
        this.maker_id = maker_id;
        this.status = status;
    }

    public Contact() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMaker_id() {
        return maker_id;
    }

    public void setMaker_id(Integer maker_id) {
        this.maker_id = maker_id;
    }

    public Date getDay_reply() {
        return day_reply;
    }

    public void setDay_reply(Date day_reply) {
        this.day_reply = day_reply;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Date getDay_send() {
        return day_send;
    }

    public void setDay_send(Date day_send) {
        this.day_send = day_send;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
