package com.fe2.project.rest.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fe2.project.rest.entities.PagesGroup;
import com.fe2.project.rest.repositories.PagesGroupsRepositories;
import com.fe2.project.rest.repositories.DTO.Response;
import com.fe2.project.rest.repositories.DTO.updatePagesGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagesGroup")
@CrossOrigin(origins = "*")

public class PagesGroupController {
    @Autowired
    private PagesGroupsRepositories pagesGroupsRepositories;

    @PostMapping("/insertPagestGroup")
    public Response insertProductGroup(@RequestBody PagesGroup pagesGroup) {
        List<PagesGroup> pagesGroups = pagesGroupsRepositories.findByName(pagesGroup.getName());
        if (pagesGroups.size() > 0) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Đã tồn tại tên nhóm trang", null);
        }
        pagesGroup.setCreated_time(new Date());
        pagesGroupsRepositories.save(pagesGroup);
        return new Response(HttpStatus.OK.value(), "success", null);
    }

    @PostMapping("/updatePagesGroup/{id}")
    public Response updatePagesGroup(@RequestBody updatePagesGroup updatePagesGroup, @PathVariable("id") Integer id) {
        Optional<PagesGroup> optional = pagesGroupsRepositories.findById(id);
        if (!optional.get().getId().equals(id)) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tôn tại id", null);
        }
        pagesGroupsRepositories.updatePagesGroup(updatePagesGroup.getName(), id);
        return new Response(HttpStatus.OK.value(), "success", null);
    }

    @GetMapping("/getPagesGroup")
    public Response getPagesGroup() {
        List<PagesGroup> pagesGroups = pagesGroupsRepositories.findAll();
        if (pagesGroups.size() == 0) {
            return new Response(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), null);
        }
        return new Response(HttpStatus.OK.value(), HttpStatus.OK.name(), pagesGroups);
    }

    @GetMapping("/getPagesGroup/{id}")
    public Response getPageGroupByID(@PathVariable("id") Integer id) {
        List<PagesGroup> pagesGroups = pagesGroupsRepositories.findPagesGroupById(id);
        if (pagesGroups.size()==0) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại group page",null);
        }
        return new Response(HttpStatus.OK.value(), "success", pagesGroups.get(0));
    }
    @DeleteMapping("/deletePagesGroup/{id}")
    public Response deletepagesGroup(@PathVariable("id") Integer id) {
        List<PagesGroup> pagesGroups = pagesGroupsRepositories.findPagesGroupById(id);
        if (pagesGroups.size()==0) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại group page",null);
        }
        pagesGroupsRepositories.deleteById(id);
        return new Response(HttpStatus.OK.value(), "success", pagesGroups.get(0));
    }
}
