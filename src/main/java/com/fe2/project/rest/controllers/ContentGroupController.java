package com.fe2.project.rest.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fe2.project.rest.entities.ContentGroup;
import com.fe2.project.rest.repositories.ContentGroupRespositories;
import com.fe2.project.rest.repositories.DTO.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contentGroup")
@CrossOrigin(origins = "*")
public class ContentGroupController {
    @Autowired
    private ContentGroupRespositories contentGroupRespositories;

    @PostMapping(value = "/insetContentGroup")
    public Response insetContentGroup(@RequestBody ContentGroup contentGroup) {
        List<ContentGroup> content_Group = contentGroupRespositories.findAll();
        if (content_Group.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại dữ liệu", null);
        }
        contentGroup.setCreate_time(new Date());
        contentGroupRespositories.save(contentGroup);
        return new Response(HttpStatus.OK.value(), "success", null);
    }

    @GetMapping(value = "/getAllContentGroup")
    public Response getAllContentGroup() {
        List<ContentGroup> contentGroup = contentGroupRespositories.findAll();
        if (contentGroup.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại dữ liệu", null);
        }
        return new Response(HttpStatus.OK.value(), "success", contentGroup);
    }

    @GetMapping(value = "/getAllContentGroupByID/{id}")
    public Response getAllContentGroupByID(@PathVariable Integer id) {
        Optional<ContentGroup> contentGroup = contentGroupRespositories.findById(id);
        if (contentGroup.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại dữ liệu", null);
        }
        return new Response(HttpStatus.OK.value(), "success", contentGroup);
    }

    @PostMapping(value = "/updateContentGroup/{id}")
    public Response updateContentGroup(@PathVariable Integer id, @RequestBody ContentGroup contentGroup) {
        Optional<ContentGroup> content_Group = contentGroupRespositories.findById(id);
        if (content_Group.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại dữ liệu", null);
        }
        contentGroupRespositories.updateContentGroup(contentGroup.getLink(), contentGroup.getName(), id);
        return new Response(HttpStatus.OK.value(), "success", null);
    }

    @DeleteMapping(value = "/deleteContentGroup/{id}")
    public Response deleteContentGroup(@PathVariable Integer id) {
        Optional<ContentGroup> content_Group = contentGroupRespositories.findById(id);
        if (content_Group.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại dữ liệu", null);
        }
        contentGroupRespositories.deleteById(id);
        return new Response(HttpStatus.OK.value(), "success", null);
    }
}
