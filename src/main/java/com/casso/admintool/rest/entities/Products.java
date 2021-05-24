package com.casso.admintool.rest.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Products implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String link;
    private Integer group_id;
    private Integer made_in_id;
    private Integer brand_id;
    private String description;
    private String detail;
    private Double price;
    private Double price_sale;
    private String images;
    private String list_images;
    private Integer count;
    private Integer status;
    private Date create_time;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getList_images() {
        return list_images;
    }

    public void setList_images(String list_images) {
        this.list_images = list_images;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Double getPrice_sale() {
        return price_sale;
    }

    public void setPrice_sale(Double price_sale) {
        this.price_sale = price_sale;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }

    public Integer getMade_in_id() {
        return made_in_id;
    }

    public void setMade_in_id(Integer made_in_id) {
        this.made_in_id = made_in_id;
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

    public Products() {
    }

    public Products(Integer id, String name, String link, Integer group_id, Integer made_in_id, Integer brand_id,
            String description, String detail, Double price, Double price_sale, String images, String list_images,
            Integer count, Integer status, Date create_time) {
        this.id = id;
        this.name = name;
        this.link = link;   
        this.group_id = group_id;
        this.made_in_id = made_in_id;
        this.brand_id = brand_id;
        this.description = description;
        this.detail = detail;
        this.price = price;
        this.price_sale = price_sale;
        this.images = images;
        this.list_images = list_images;
        this.count = count;
        this.status = status;
        this.create_time = create_time;
    }
}
