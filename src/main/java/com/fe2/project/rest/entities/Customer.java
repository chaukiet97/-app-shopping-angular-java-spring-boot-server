package com.fe2.project.rest.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Customer implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private Integer sex;
    private Date brithday;
    private String password;
    private Integer status;
    private String address;
    private Date create_time;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBrithday() {
        return brithday;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Customer() {
    }

    public Customer(Integer id, String name, String email, String phone, Integer sex, Date brithday, String password,
            Integer status, Date create_time, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
        this.brithday = brithday;
        this.password = password;
        this.status = status;
        this.create_time = create_time;
        this.address = address;
    }
}
