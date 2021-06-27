package com.fe2.project.rest.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fe2.project.rest.entities.Brand;
import com.fe2.project.rest.entities.MadeIn;
import com.fe2.project.rest.entities.Products;
import com.fe2.project.rest.entities.ProductsGroup;
import com.fe2.project.rest.repositories.BrandRepositories;
import com.fe2.project.rest.repositories.MadeInRepositories;
import com.fe2.project.rest.repositories.ProductsGroupRepositories;
import com.fe2.project.rest.repositories.ProductsRepositories;
import com.fe2.project.rest.repositories.DTO.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductsController {

    @Autowired
    private ProductsGroupRepositories productsGroupRepositories;
    @Autowired
    private MadeInRepositories madeInRepositories;
    @Autowired
    private BrandRepositories brandRepositories;
    @Autowired
    private ProductsRepositories productsRepositories;

    // Product Group
    @PostMapping("/insertProductGroup")
    public Response insertProductGroup(@RequestBody ProductsGroup productsGroup) {
        List<ProductsGroup> group = productsGroupRepositories.findByName(productsGroup.getName());
        if (group.size() == 1) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Đã tồn tại tên nhóm trang", null);
        }
        productsGroup.setCreated_time(new Date());
        productsGroupRepositories.save(productsGroup);
        return new Response(HttpStatus.OK.value(), "success", null);
    }

    @PostMapping("/updateProductGroup/{id}")
    public Response updateProductGroup(@RequestBody ProductsGroup productsGroup, @PathVariable("id") Integer id) {
        Optional<ProductsGroup> optional = productsGroupRepositories.findById(id);
        if (!optional.get().getId().equals(id)) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại ID", null);
        }
        productsGroupRepositories.updateProductGroup(productsGroup.getName(), id);

        return new Response(HttpStatus.OK.value(), "success", null);
    }

    @GetMapping("/getProductGroup")
    public Response getProductGroup() {
        List<ProductsGroup> productsGroup = productsGroupRepositories.findAll();
        if (productsGroup.size() == 0) {
            return new Response(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), null);
        }
        return new Response(HttpStatus.OK.value(), "success", productsGroup);
    }

    @GetMapping("/getProductGroup/{id}")
    public Response getProductGroupByID(@PathVariable("id") Integer id) {
        Optional<ProductsGroup> optional = productsGroupRepositories.findById(id);
        if (!optional.get().getId().equals(id)) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại dữ liệu", null);
        }
        return new Response(HttpStatus.OK.value(), "success", optional.get());
    }

    @DeleteMapping("/deleteProductGroup/{id}")
    public Response deleteProductGroup(@PathVariable("id") Integer id) {
        Optional<ProductsGroup> optional = productsGroupRepositories.findById(id);
        if (!optional.get().getId().equals(id)) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại ID", null);
        }
        productsGroupRepositories.deleteById(id);

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
    public Response getAllBrand() {
        List<Brand> brands = brandRepositories.findAll();
        if (brands.size() == 0) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không có dự liệu", null);
        }
        return new Response(HttpStatus.OK.value(), "success", brands);
    }

    @GetMapping("/getBrand/{id}")
    public Response getBrandById(@PathVariable("id") Integer id) {
        Optional<Brand> brands = brandRepositories.findById(id);
        if (!brands.get().getId().equals(id)) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại ID", null);
        }
        return new Response(HttpStatus.OK.value(), "success", brands.get());
    }

    @PostMapping("/updateBrand/{id}")
    public Response updateBrand(@RequestBody Brand brand, @PathVariable("id") Integer id) {
        Optional<Brand> optional = brandRepositories.findById(id);
        if (!optional.get().getId().equals(id)) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại ID", null);
        }
        brandRepositories.updateMadeIn(brand.getName(), id);
        return new Response(HttpStatus.OK.value(), "success", null);
    }

    @DeleteMapping("/deleteBrand/{id}")
    public Response deleteBrand(@PathVariable("id") Integer id) {
        Optional<Brand> optional = brandRepositories.findById(id);
        if (!optional.get().getId().equals(id)) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại ID", null);
        }
        brandRepositories.deleteById(id);
        return new Response(HttpStatus.OK.value(), "success", null);
    }

    // add Product
    @PostMapping("/inserProduct")
    public Response inserProduct(@RequestBody Products products) {

        List<Products> list = productsRepositories.findByName(products.getName());
        if (list.size() > 0) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Đã tồn tại tên sản phẩm", null);
        }
        products.setCreate_time(new Date());
        products.setStatus(1);
        productsRepositories.save(products);
        return new Response(HttpStatus.OK.value(), "success", null);
    }

    @GetMapping("/getAllProduct")
    public Response getAllProduct() {
        List<Products> products = productsRepositories.findAll();
        if (products.size() == 0) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không có dự liệu", null);
        }
        return new Response(HttpStatus.OK.value(), "success", products);
    }

    @GetMapping("getProduct/{id}")
    public Response getProductByID(@PathVariable("id") Integer id) {
        Optional<Products> optional = productsRepositories.findById(id);
        if (!optional.get().getId().equals(id)) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại ID", null);
        }
        return new Response(HttpStatus.OK.value(), "success", optional.get());
    }

    @PostMapping("/updateProduct/{id}")
    public Response updateProduct(@PathVariable("id") Integer id, @RequestBody Products products) {
        Optional<Products> optional = productsRepositories.findById(id);
        if (!optional.get().getId().equals(id)) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại ID", null);
        }
        productsRepositories.updateProduct(products.getBrand_id(), products.getDescription(), products.getDetail(),
                products.getImages(), products.getLink(), products.getList_images(), products.getMade_in_id(),
                products.getName(), products.getPrice(), products.getStatus(), products.getCount(),
                products.getGroup_id(), products.getPrice_sale(),products.getType(), id);
        return new Response(HttpStatus.OK.value(), "success", null);
    }
    @DeleteMapping("/deleteProduct/{id}")
    public Response deleteProduct(@PathVariable Integer id){
        Optional<Products> optional = productsRepositories.findById(id);
        if (!optional.get().getId().equals(id)) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại ID", null);
        }
        productsRepositories.deleteById(id);
        return new Response(HttpStatus.OK.value(), "success", null);
    }
}
