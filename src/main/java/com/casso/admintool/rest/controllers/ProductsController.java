package com.casso.admintool.rest.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.casso.admintool.rest.entities.Brand;
import com.casso.admintool.rest.entities.MadeIn;
import com.casso.admintool.rest.entities.ProductsGroup;
import com.casso.admintool.rest.repositories.BrandRepositories;
import com.casso.admintool.rest.repositories.MadeInRepositories;
import com.casso.admintool.rest.repositories.ProductsRepositories;
import com.casso.admintool.rest.repositories.DTO.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductsController {

    @Autowired
    private ProductsRepositories productsRepositories;
    @Autowired
    private MadeInRepositories madeInRepositories;
    @Autowired
    private BrandRepositories brandRepositories;

    // Product Group
    @PostMapping("/insertProductGroup")
    public Response insertProductGroup(@RequestBody ProductsGroup productsGroup) {
        List<ProductsGroup> group = productsRepositories.findByName(productsGroup.getName());
        if (group.size() == 1) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Đã tồn tại tên nhóm trang", null);
        }
        productsGroup.setCreated_time(new Date());
        productsRepositories.save(productsGroup);
        return new Response(HttpStatus.OK.value(), "success", null);
    }
    @PostMapping("/updateProductGroup/{id}")
    public Response updateProductGroup(@RequestBody ProductsGroup productsGroup, @PathVariable("id") Integer id) {
        Optional<ProductsGroup> optional = productsRepositories.findById(id);
        if (!optional.get().getId().equals(id)) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại ID", null);
        }
        productsRepositories.updateProductGroup(productsGroup.getName(), id);

        return new Response(HttpStatus.OK.value(), "success", null);
    }
    @GetMapping("/getProductGroup")
    public Response getProductGroup() {
        List<ProductsGroup> productsGroup = productsRepositories.findAll();
        if (productsGroup.size() == 0) {
            return new Response(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), null);
        }
        return new Response(HttpStatus.OK.value(), "success", productsGroup);
    }
    @GetMapping("/getProductGroup/{id}")
    public Response getProductGroupByID(@PathVariable("id") Integer id) {
        Optional<ProductsGroup> optional = productsRepositories.findById(id);
        if (!optional.get().getId().equals(id)) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại dữ liệu", null);
        }
        return new Response(HttpStatus.OK.value(), "success", optional.get());
    }
    @DeleteMapping("/deleteProductGroup/{id}")
    public Response deleteProductGroup(@PathVariable("id") Integer id) {
        Optional<ProductsGroup> optional = productsRepositories.findById(id);
        if (!optional.get().getId().equals(id)) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại ID", null);
        }
        productsRepositories.deleteById(id);

        return new Response(HttpStatus.OK.value(), "success", null);
    }

    // Made IN
    @PostMapping("/insertMadeIn")
    public Response insertMadeIn(@RequestBody MadeIn madeIn) {
        List<MadeIn> list = madeInRepositories.findByName(madeIn.getName());
        if (list.size() > 0) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Đã tồn tại nơi sản xuất", null);
        }
        madeIn.setCreate_time(new Date());
        madeInRepositories.save(madeIn);
        return new Response(HttpStatus.OK.value(), "success", null);
    }
    @GetMapping("/getMadeIn")
    public Response getAllMadeIn() {
        List<MadeIn> madeIns = madeInRepositories.findAll();
        if (madeIns.size() == 0) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không có nơi sản xuất", null);
        }
        return new Response(HttpStatus.OK.value(), "success", madeIns);
    }
    @GetMapping("/getMadeIn/{id}")
    public Response getMadeInByID(@PathVariable("id") Integer id) {
        Optional<MadeIn> optional = madeInRepositories.findById(id);
        if (!optional.get().getId().equals(id)) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại dữ liệu", null);
        }
        return new Response(HttpStatus.OK.value(), "success", optional.get());
    }
    @PostMapping("/updateMadeIn/{id}")
    public Response updateMadeIn(@RequestBody MadeIn madeIn, @PathVariable("id") Integer id) {
        Optional<MadeIn> optional = madeInRepositories.findById(id);
        if (!optional.get().getId().equals(id)) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại ID", null);
        }
        madeInRepositories.updateMadeIn(madeIn.getName(), id);

        return new Response(HttpStatus.OK.value(), "success", null);
    }
    @DeleteMapping("/deleteMadeIn/{id}")
    public Response deleteMadeIn(@PathVariable("id") Integer id) {
        Optional<MadeIn> optional = madeInRepositories.findById(id);
        if (!optional.get().getId().equals(id)) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại ID", null);
        }
        madeInRepositories.deleteById(id);

        return new Response(HttpStatus.OK.value(), "success", null);
    }

    // brand product
    @PostMapping("/insertBrand")
    public Response insertBrand(@RequestBody Brand brand) {
        List<Brand> list = brandRepositories.findByName(brand.getName());
        if (list.size() > 0) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Đã tồn tại nơi sản xuất", null);
        }
        brand.setCreate_time(new Date());
        brandRepositories.save(brand);
        return new Response(HttpStatus.OK.value(), "success", null);
    }
    @GetMapping("/getBrand")
    public Response getAllBrand(){
        List<Brand> brands = brandRepositories.findAll();
        if (brands.size() == 0) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không có dự liệu", null);
        }
        return new Response(HttpStatus.OK.value(), "success", brands);
    }
    @GetMapping("/getBrand/{id}")
    public Response getBrandById(@PathVariable("id") Integer id){
        Optional<Brand> brands = brandRepositories.findById(id);
        if (!brands.get().getId().equals(id)) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại ID", null);
        }
        return new Response(HttpStatus.OK.value(), "success", brands.get());
    }
    @PostMapping("/updateBrand/{id}")
    public Response updateBrand(@RequestBody Brand brand, @PathVariable("id") Integer id){
        Optional<Brand> optional = brandRepositories.findById(id);
        if (!optional.get().getId().equals(id)) {
            return new Response(HttpStatus.NOT_FOUND.value(),"Không tồn tại ID" , null);
        }
        brandRepositories.updateMadeIn(brand.getName(), id);
        return new Response(HttpStatus.OK.value(), "success", null);
    }
    @DeleteMapping("/deleteBrand/{id}")
    public Response deleteBrand(@PathVariable("id") Integer id){
        Optional<Brand> optional = brandRepositories.findById(id);
        if (!optional.get().getId().equals(id)) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại ID", null);
        }
        brandRepositories.deleteById(id);
        return new Response(HttpStatus.OK.value(), "success", null);
    }
}
