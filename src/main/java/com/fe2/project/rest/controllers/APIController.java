package com.fe2.project.rest.controllers;

import java.util.List;

import com.fe2.project.rest.entities.Page;
import com.fe2.project.rest.repositories.PageRepositories;
import com.fe2.project.rest.repositories.DTO.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class APIController {
    @Autowired
    private PageRepositories pageRepositories;

    @GetMapping("/getMenu/{id}")
    public Response getMenu(@PathVariable Integer id) {
        List<Page> menu = pageRepositories.menu(id);
        if (menu.size() == 0) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không có menu", null);
        }
        return new Response(HttpStatus.OK.value(), "success", menu);
    }
}
