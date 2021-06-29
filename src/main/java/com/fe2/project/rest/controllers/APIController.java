package com.fe2.project.rest.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fe2.project.rest.entities.Banner;
import com.fe2.project.rest.entities.Contact;
import com.fe2.project.rest.entities.Customer;
import com.fe2.project.rest.entities.Page;
import com.fe2.project.rest.entities.Website;
import com.fe2.project.rest.repositories.BannerRepositories;
import com.fe2.project.rest.repositories.ContactRepositories;
import com.fe2.project.rest.repositories.ContentRespositories;
import com.fe2.project.rest.repositories.CustomerRespositories;
import com.fe2.project.rest.repositories.PageRepositories;
import com.fe2.project.rest.repositories.ProductsRepositories;
import com.fe2.project.rest.repositories.WebsiteRepositories;
import com.fe2.project.rest.repositories.DTO.ContentDTO;
import com.fe2.project.rest.repositories.DTO.Login;
import com.fe2.project.rest.repositories.DTO.ProductResponse;
import com.fe2.project.rest.repositories.DTO.Response;
import com.fe2.project.rest.repositories.DTO.SearchDTO;
import com.fe2.util.SHA512;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class APIController {
    @Autowired
    private PageRepositories pageRepositories;
    @Autowired
    private ContactRepositories contactRepositories;
    @Autowired
    private BannerRepositories bannerRepositories;
    @Autowired
    private WebsiteRepositories websiteRepositories;
    @Autowired
    private ProductsRepositories productsRepositories;
    @Autowired
    private CustomerRespositories customerRespositories;

    @Autowired
    private ContentRespositories contentRespositories;

    @GetMapping("/getMenu/{id}")
    public Response getMenu(@PathVariable Integer id) {
        List<Page> menu = pageRepositories.menu(id);
        if (menu.size() == 0) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không có menu", null);
        }
        return new Response(HttpStatus.OK.value(), "success", menu);
    }

    @PostMapping("/addContact")
    public Response addContact(@RequestBody Contact contact) {
        contact.setStatus(0);
        contact.setDay_send(new Date());
        contactRepositories.save(contact);
        return new Response(HttpStatus.OK.value(), "success", null);
    }

    @GetMapping(value = "/getBannerHeaderTop")
    public Response getBannerHeaderTop() {
        Optional<Banner> banner = bannerRepositories.findById(4);
        if (banner.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không có hình quảng cáo", null);
        }
        return new Response(HttpStatus.OK.value(), "success", banner.get());
    }

    @GetMapping(value = "/getCompany")
    public Response getCompany() {
        Optional<Website> website = websiteRepositories.findById(1);
        if (website.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không có thông tin Website", null);
        }
        return new Response(HttpStatus.OK.value(), "success", website.get());
    }

    @GetMapping(value = "/getAllSlide")
    public Response getAllSlide() {
        List<Banner> banner = bannerRepositories.getAllSlide();
        if (banner.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không có thông tin Website", null);
        }
        return new Response(HttpStatus.OK.value(), "success", banner);
    }

    @GetMapping(value = "/getAllProduct")
    public Response getAllProduct() {
        List<Object[]> objects = productsRepositories.findAllProduct();
        if (objects.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không có sản phẩm", null);
        }
        ArrayList<ProductResponse> list = new ArrayList<ProductResponse>();
        for (Object[] obj : objects) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId((Integer) obj[0]);
            productResponse.setBrand_id((Integer) obj[1]);
            productResponse.setCount((Integer) obj[2]);
            productResponse.setCreate_time((Date) obj[3]);
            productResponse.setDescription((String) obj[4]);
            productResponse.setDetail((String) obj[5]);
            productResponse.setGroup_id((Integer) obj[6]);
            productResponse.setImages((String) obj[7]);
            productResponse.setLink((String) obj[8]);
            productResponse.setList_images((String) obj[9]);
            productResponse.setMade_in_id((Integer) obj[10]);
            productResponse.setName((String) obj[11]);
            productResponse.setPrice((Double) obj[12]);
            productResponse.setPrice_sale((Double) obj[13]);
            productResponse.setStatus((Integer) obj[14]);
            productResponse.setType((Integer) obj[15]);
            productResponse.setParent_link((String) obj[16]);
            productResponse.setName_group((String) obj[17]);
            productResponse.setName_brand((String) obj[18]);
            productResponse.setName_made((String) obj[19]);
            list.add(productResponse);
        }
        return new Response(HttpStatus.OK.value(), "success", list);
    }

    @GetMapping(value = "/getProduct/{link}")
    public Response getProductByLink(@PathVariable String link) {
        List<Object[]> objects = productsRepositories.findProductByLink(link);
        if (objects.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không có sản phẩm", null);
        }
        ArrayList<ProductResponse> list = new ArrayList<ProductResponse>();
        for (Object[] obj : objects) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId((Integer) obj[0]);
            productResponse.setBrand_id((Integer) obj[1]);
            productResponse.setCount((Integer) obj[2]);
            productResponse.setCreate_time((Date) obj[3]);
            productResponse.setDescription((String) obj[4]);
            productResponse.setDetail((String) obj[5]);
            productResponse.setGroup_id((Integer) obj[6]);
            productResponse.setImages((String) obj[7]);
            productResponse.setLink((String) obj[8]);
            productResponse.setList_images((String) obj[9]);
            productResponse.setMade_in_id((Integer) obj[10]);
            productResponse.setName((String) obj[11]);
            productResponse.setPrice((Double) obj[12]);
            productResponse.setPrice_sale((Double) obj[13]);
            productResponse.setStatus((Integer) obj[14]);
            productResponse.setType((Integer) obj[15]);
            productResponse.setParent_link((String) obj[16]);
            productResponse.setName_group((String) obj[17]);
            productResponse.setName_brand((String) obj[18]);
            productResponse.setName_made((String) obj[19]);
            list.add(productResponse);
        }
        return new Response(HttpStatus.OK.value(), "success", list);

    }

    @GetMapping(value = "/getProductDetail/{link}")
    public Response getProductDetailByLink(@PathVariable String link) {
        List<Object[]> objects = productsRepositories.findProductDetailByLink(link);
        if (objects.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không có sản phẩm", null);
        }
        ArrayList<ProductResponse> list = new ArrayList<ProductResponse>();
        for (Object[] obj : objects) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId((Integer) obj[0]);
            productResponse.setBrand_id((Integer) obj[1]);
            productResponse.setCount((Integer) obj[2]);
            productResponse.setCreate_time((Date) obj[3]);
            productResponse.setDescription((String) obj[4]);
            productResponse.setDetail((String) obj[5]);
            productResponse.setGroup_id((Integer) obj[6]);
            productResponse.setImages((String) obj[7]);
            productResponse.setLink((String) obj[8]);
            productResponse.setList_images((String) obj[9]);
            productResponse.setMade_in_id((Integer) obj[10]);
            productResponse.setName((String) obj[11]);
            productResponse.setPrice((Double) obj[12]);
            productResponse.setPrice_sale((Double) obj[13]);
            productResponse.setStatus((Integer) obj[14]);
            productResponse.setType((Integer) obj[15]);
            productResponse.setParent_link((String) obj[16]);
            productResponse.setName_group((String) obj[17]);
            productResponse.setName_brand((String) obj[18]);
            productResponse.setName_made((String) obj[19]);
            list.add(productResponse);
        }
        return new Response(HttpStatus.OK.value(), "success", list);

    }

    @GetMapping(value = "/getProductById/{id}")
    public Response getProductById(@PathVariable Integer id) {
        List<Object[]> objects = productsRepositories.findProductByID(id);
        if (objects.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không có sản phẩm", null);
        }
        ArrayList<ProductResponse> list = new ArrayList<ProductResponse>();
        for (Object[] obj : objects) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId((Integer) obj[0]);
            productResponse.setBrand_id((Integer) obj[1]);
            productResponse.setCount((Integer) obj[2]);
            productResponse.setCreate_time((Date) obj[3]);
            productResponse.setDescription((String) obj[4]);
            productResponse.setDetail((String) obj[5]);
            productResponse.setGroup_id((Integer) obj[6]);
            productResponse.setImages((String) obj[7]);
            productResponse.setLink((String) obj[8]);
            productResponse.setList_images((String) obj[9]);
            productResponse.setMade_in_id((Integer) obj[10]);
            productResponse.setName((String) obj[11]);
            productResponse.setPrice((Double) obj[12]);
            productResponse.setPrice_sale((Double) obj[13]);
            productResponse.setStatus((Integer) obj[14]);
            productResponse.setType((Integer) obj[15]);
            productResponse.setParent_link((String) obj[16]);
            productResponse.setName_group((String) obj[17]);
            productResponse.setName_brand((String) obj[18]);
            productResponse.setName_made((String) obj[19]);
            list.add(productResponse);
        }
        return new Response(HttpStatus.OK.value(), "success", list);
    }

    @PostMapping(value = "/searchProduct")
    public Response searchProduct(@RequestBody SearchDTO searchDTO) {
        List<Object[]> objects = productsRepositories.searchProduct(searchDTO.getLink());
        if (objects.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không có sản phẩm", null);
        }
        ArrayList<ProductResponse> list = new ArrayList<ProductResponse>();
        for (Object[] obj : objects) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId((Integer) obj[0]);
            productResponse.setBrand_id((Integer) obj[1]);
            productResponse.setCount((Integer) obj[2]);
            productResponse.setCreate_time((Date) obj[3]);
            productResponse.setDescription((String) obj[4]);
            productResponse.setDetail((String) obj[5]);
            productResponse.setGroup_id((Integer) obj[6]);
            productResponse.setImages((String) obj[7]);
            productResponse.setLink((String) obj[8]);
            productResponse.setList_images((String) obj[9]);
            productResponse.setMade_in_id((Integer) obj[10]);
            productResponse.setName((String) obj[11]);
            productResponse.setPrice((Double) obj[12]);
            productResponse.setPrice_sale((Double) obj[13]);
            productResponse.setStatus((Integer) obj[14]);
            productResponse.setType((Integer) obj[15]);
            productResponse.setParent_link((String) obj[16]);
            productResponse.setName_group((String) obj[17]);
            productResponse.setName_brand((String) obj[18]);
            productResponse.setName_made((String) obj[19]);
            list.add(productResponse);
        }
        return new Response(HttpStatus.OK.value(), "success", list);
    }

    @GetMapping(value = "/getProductByType/{type}")
    public Response getProductByType(@PathVariable Integer type) {
        List<Object[]> objects = productsRepositories.findProductByType(type);
        if (objects.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không có sản phẩm", null);
        }
        ArrayList<ProductResponse> list = new ArrayList<ProductResponse>();
        for (Object[] obj : objects) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId((Integer) obj[0]);
            productResponse.setBrand_id((Integer) obj[1]);
            productResponse.setCount((Integer) obj[2]);
            productResponse.setCreate_time((Date) obj[3]);
            productResponse.setDescription((String) obj[4]);
            productResponse.setDetail((String) obj[5]);
            productResponse.setGroup_id((Integer) obj[6]);
            productResponse.setImages((String) obj[7]);
            productResponse.setLink((String) obj[8]);
            productResponse.setList_images((String) obj[9]);
            productResponse.setMade_in_id((Integer) obj[10]);
            productResponse.setName((String) obj[11]);
            productResponse.setPrice((Double) obj[12]);
            productResponse.setPrice_sale((Double) obj[13]);
            productResponse.setStatus((Integer) obj[14]);
            productResponse.setType((Integer) obj[15]);
            productResponse.setParent_link((String) obj[16]);
            productResponse.setName_group((String) obj[17]);
            productResponse.setName_brand((String) obj[18]);
            productResponse.setName_made((String) obj[19]);
            list.add(productResponse);
        }
        return new Response(HttpStatus.OK.value(), "success", list);
    }

    @PostMapping("/login")
    public Response Login(@RequestBody Login login) {
        List<Customer> customers = customerRespositories.findByEmail(login.getEmail());
        if (customers.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Email không tồn tại", null);
        }

        String passwordHashServer = customers.get(0).getPassword();
        String passwordHashClient = login.getPassword();
        passwordHashClient = SHA512.encryptThisString(passwordHashClient);
        if (!passwordHashServer.equals(passwordHashClient)) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Email hoặc mật khẩu không chính xác", null);
        }
        return new Response(HttpStatus.OK.value(), "success", customers);
    }

    @GetMapping(value = "/getContentHome")
    public Response getContentHome() {
        List<Object[]> contents = contentRespositories.getContentHome();
        if (contents.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không có sản phẩm", null);
        }
        ArrayList<ContentDTO> list = new ArrayList<ContentDTO>();
        for (Object[] obj : contents) {
            ContentDTO contentDTO = new ContentDTO();
            contentDTO.setId((Integer) obj[0]);
            contentDTO.setCreate_time((Date) obj[1]);
            contentDTO.setDescription((String) obj[2]);
            contentDTO.setDetail((String) obj[3]);
            contentDTO.setGroup_id((Integer) obj[4]);
            contentDTO.setImages((String) obj[5]);
            contentDTO.setLink((String) obj[6]);
            contentDTO.setName((String) obj[7]);
            contentDTO.setStatus((Integer) obj[8]);
            contentDTO.setName_group((String) obj[9]);
            contentDTO.setParent_link((String) obj[10]);
            list.add(contentDTO);
        }
        return new Response(HttpStatus.OK.value(), "success", list);
    }
    @GetMapping(value = "/getContentGroup/{link}")
    public Response getContentGroup(@PathVariable String link){
        List<Object[]> contents = contentRespositories.getContentByLink(link);
        if (contents.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không có sản phẩm", null);
        }
        ArrayList<ContentDTO> list = new ArrayList<ContentDTO>();
        for (Object[] obj : contents) {
            ContentDTO contentDTO = new ContentDTO();
            contentDTO.setId((Integer) obj[0]);
            contentDTO.setCreate_time((Date) obj[1]);
            contentDTO.setDescription((String) obj[2]);
            contentDTO.setDetail((String) obj[3]);
            contentDTO.setGroup_id((Integer) obj[4]);
            contentDTO.setImages((String) obj[5]);
            contentDTO.setLink((String) obj[6]);
            contentDTO.setName((String) obj[7]);
            contentDTO.setStatus((Integer) obj[8]);
            contentDTO.setName_group((String) obj[9]);
            contentDTO.setParent_link((String) obj[10]);
            list.add(contentDTO);
        }
        return new Response(HttpStatus.OK.value(), "success", list);
    }

    @GetMapping(value = "/getContentByLink/{link}")
    public Response getContentByLink(@PathVariable String link){
        List<Object[]> contents = contentRespositories.getContentDetail(link);
        if (contents.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không có sản phẩm", null);
        }
        ArrayList<ContentDTO> list = new ArrayList<ContentDTO>();
        for (Object[] obj : contents) {
            ContentDTO contentDTO = new ContentDTO();
            contentDTO.setId((Integer) obj[0]);
            contentDTO.setCreate_time((Date) obj[1]);
            contentDTO.setDescription((String) obj[2]);
            contentDTO.setDetail((String) obj[3]);
            contentDTO.setGroup_id((Integer) obj[4]);
            contentDTO.setImages((String) obj[5]);
            contentDTO.setLink((String) obj[6]);
            contentDTO.setName((String) obj[7]);
            contentDTO.setStatus((Integer) obj[8]);
            contentDTO.setName_group((String) obj[9]);
            contentDTO.setParent_link((String) obj[10]);
            list.add(contentDTO);
        }
        return new Response(HttpStatus.OK.value(), "success", list);
    }

}
