package com.casso.admintool.rest.controllers;

import java.util.Date;
import java.util.List;

import com.casso.admintool.rest.entities.ProductsGroup;
import com.casso.admintool.rest.repositories.ProductsRepositories;
import com.casso.admintool.rest.repositories.DTO.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productsGroup")
@CrossOrigin(origins = "*")
public class ProductsController {

    @Autowired
    private ProductsRepositories productsRepositories;

    @PostMapping("/insertProductGroup")
    public Response insertProductGroup(@RequestBody ProductsGroup productsGroup) {
        List<ProductsGroup> group = productsRepositories.findByName(productsGroup.getName());
        if (group.size()==1) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Đã tồn tại tên nhóm trang", null);
        }
        productsGroup.setCreated_time(new Date());
        productsRepositories.save(productsGroup);
        return new Response(HttpStatus.OK.value(), "success", null);
    }
    @GetMapping("/getProductGroup")
    public Response getProductGroup() {
        List<ProductsGroup> productsGroup = productsRepositories.findAll();
        if (productsGroup.size() ==0) {
            return new Response(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), null);
        }
        return new Response(HttpStatus.OK.value(), HttpStatus.OK.name(), productsGroup);
    }


}
