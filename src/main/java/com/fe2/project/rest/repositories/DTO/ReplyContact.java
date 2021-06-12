package com.fe2.project.rest.repositories.DTO;

import java.util.Date;

public class ReplyContact {
    private String reply;
    private Date day_reply;
    private Integer status;
    private Integer maker_id;

    public ReplyContact(String reply, Date day_reply, Integer status, Integer maker_id) {
        this.reply = reply;
        this.day_reply = day_reply;
        this.status = status;
        this.maker_id = maker_id;
    }

    public Integer getMaker_id() {
        return maker_id;
    }

    public void setMaker_id(Integer maker_id) {
        this.maker_id = maker_id;
    }

    public ReplyContact() {
    }

    public String getReply() {
        return reply;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDay_reply() {
        return day_reply;
    }

    public void setDay_reply(Date day_reply) {
        this.day_reply = day_reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

}
