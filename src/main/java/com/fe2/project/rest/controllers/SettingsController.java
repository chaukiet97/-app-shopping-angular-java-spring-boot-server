package com.fe2.project.rest.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fe2.project.rest.entities.Banner;
import com.fe2.project.rest.entities.Website;
import com.fe2.project.rest.repositories.BannerRepositories;
import com.fe2.project.rest.repositories.WebsiteRepositories;
import com.fe2.project.rest.repositories.DTO.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/settings")
@CrossOrigin(origins = "*")
public class SettingsController {
    @Autowired
    private WebsiteRepositories websiteRepositories;
    @Autowired
    private BannerRepositories bannerRepositories;

    // api settings website
    @PostMapping("/website/insertWebsite")
    public Response insertWebsite(@RequestBody Website website) {
        websiteRepositories.save(website);
        return new Response(HttpStatus.OK.value(), "success", null);
    }

    @GetMapping("/website/getProfileWebsite")
    public Response getProfileWebsite() {
        Optional<Website> website = websiteRepositories.findById(1);
        if (website.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại dữ liệu", null);
        }
        return new Response(HttpStatus.OK.value(), "success", website);
    }

    @PostMapping("/website/updateWebsite/{id}")
    public Response updateWebsite(@RequestBody Website website, @PathVariable Integer id) {
        websiteRepositories.updateWebsite(website.getDescription(), website.getLogo(), website.getShortcut(),
                website.getTitle(), id);
        return new Response(HttpStatus.OK.value(), "success", null);
    }

    // api settings banner
    @GetMapping("/banner/getAllBanner")
    public Response getAllBanner() {
        List<Banner> banner = bannerRepositories.findAll();
        if (banner.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại dữ liệu", null);
        }
        return new Response(HttpStatus.OK.value(), "success", banner);
    }

    @PostMapping("/banner/insertBanner")
    public Response insertBanner(@RequestBody Banner banner) {
        List<Banner> list_banner = bannerRepositories.findByName(banner.getName());
        if (!list_banner.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Đã tồn tại tên Banner", null);
        }
        banner.setCreate_time(new Date());
        bannerRepositories.save(banner);
        return new Response(HttpStatus.OK.value(), "success", null);
    }

    @GetMapping("/banner/getBannerByID/{id}")
    public Response getBannerByID(@PathVariable Integer id) {
        Optional<Banner> banner = bannerRepositories.findById(id);
        if (banner.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại dữ liệu", null);
        }
        return new Response(HttpStatus.OK.value(), "success", banner);

    }
    @PostMapping(value="/banner/updateBanner/{id}")
    public Response updateBanner(@RequestBody Banner banner, @PathVariable Integer id) {
        //TODO: process POST request
        Optional<Banner> listBanner = bannerRepositories.findById(id);
        if (listBanner.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại dữ liệu", null);
            
        }
        bannerRepositories.updateBanner(banner.getImages(), banner.getLink(), banner.getName(), banner.getStatus(), banner.getType(), banner.getDescription(), id);
        return new Response(HttpStatus.OK.value(), "success", null);
        
    }
    @DeleteMapping(value = "banner/deleteBannerByID/{id}")
    public Response deleteBannerByID(@PathVariable Integer id){
        Optional<Banner> banner = bannerRepositories.findById(id);
        if (banner.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại dữ liệu", null);
        }
        bannerRepositories.deleteById(id);
        return new Response(HttpStatus.OK.value(), "success", null);

    }
    
}
