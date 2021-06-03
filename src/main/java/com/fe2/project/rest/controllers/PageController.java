package com.fe2.project.rest.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fe2.project.rest.entities.Page;
import com.fe2.project.rest.repositories.PageRepositories;
import com.fe2.project.rest.repositories.DTO.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/page")
@CrossOrigin(origins = "*")
public class PageController {
    @Autowired
    private PageRepositories pageRepositories;

    @PostMapping("/insertPage")
    public Response insertPage(@RequestBody Page page) {
        List<Page> pagesGroups = pageRepositories.findByName(page.getName());
        if (pagesGroups.size() > 0) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Đã tồn tại tên trang", null);
        }
        page.setCreate_time(new Date());
        pageRepositories.save(page);
        return new Response(HttpStatus.OK.value(), "success", null);
    }

    @GetMapping("/getPages")
    public Response getPages() {
        List<Page> pages = pageRepositories.findAll();
        if (pages.size() == 0) {
            return new Response(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), null);
        }
        return new Response(HttpStatus.OK.value(), HttpStatus.OK.name(), pages);
    }

    @GetMapping("/getPages/{id}")
    public Response getPageByID(@PathVariable("id") Integer id) {
        Optional<Page> page = pageRepositories.findById(id);
        if (!page.get().getId().equals(id)) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại group page", null);
        }
        return new Response(HttpStatus.OK.value(), "success", page.get());
    }

    @PostMapping("/updatePage/{id}")
    public Response updatePage(@RequestBody Page updatePages, @PathVariable("id") Integer id) {
        Optional<Page> optional = pageRepositories.findById(id);
        if (!optional.get().getId().equals(id)) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tôn tại id", null);
        }
        pageRepositories.updatePage(updatePages.getDescription(), updatePages.getDetail(), updatePages.getGroup_id(),
                updatePages.getLink(), updatePages.getName(), updatePages.getStatus(), updatePages.getType(), id);

        return new Response(HttpStatus.OK.value(), "success", null);
    }

    @DeleteMapping("/deletePage/{id}")
    public Response deleteePage(@PathVariable("id") Integer id) {
        Optional<Page> optional = pageRepositories.findById(id);
        if (!optional.get().getId().equals(id)) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tôn tại id", null);
        }
        pageRepositories.deleteById(id);
        return new Response(HttpStatus.OK.value(), "success", null);
    }

    @GetMapping("/pageByType")
    public Response getPageByType() {
        List<Page> list =pageRepositories.getPageByType();
        if (list.size()==0) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại dữ liệu", null);
        }
        return new Response(HttpStatus.OK.value(), "success", list);
    }
}
