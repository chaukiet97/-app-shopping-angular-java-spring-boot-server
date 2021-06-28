package com.fe2.project.rest.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fe2.project.rest.entities.Content;
import com.fe2.project.rest.repositories.ContentRespositories;
import com.fe2.project.rest.repositories.DTO.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content")
@CrossOrigin(origins = "*")

public class ContentController {
    @Autowired
    private ContentRespositories contentRespositories;

    @PostMapping(value = "/insertContent")
    public Response insertContent(@RequestBody Content content) {
        // List<Content> list= contentRespositories.findByName(content.getName());
        // if (list.size()>0) {
        //     return new Response(HttpStatus.NOT_FOUND.value(), "Đã tồn tại tên Tin Tức", null);
        // }
        content.setCreate_time(new Date());
        contentRespositories.save(content);
        return new Response(HttpStatus.OK.value(), "success", null);
    }

    @PostMapping(value = "/updateContent/{id}")
    public Response updateContent(@RequestBody Content content, @PathVariable Integer id) {
        Optional<Content> optional = contentRespositories.findById(id);
        if (optional.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại dữ liệu", null);
        }
        contentRespositories.updateContent(content.getCreate_time(), content.getDescription(), content.getDetail(),
                content.getGroup_id(), content.getImages(), content.getLink(), content.getName(), content.getStatus(),
                id);
        return new Response(HttpStatus.OK.value(), "success", null);
    }
    @GetMapping(value = "/getAllContent")
    public Response getAllContent(){
        List<Content> contents = contentRespositories.findAll();
        if (contents.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại dữ liệu", null);
        }
        return new Response(HttpStatus.OK.value(), "success", contents);

    }
    @GetMapping(value = "/getContentById/{id}")
    public Response getContentById(@PathVariable Integer id){
        Optional<Content>contents = contentRespositories.findById(id);
        if (contents.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại dữ liệu", null);
        }
        return new Response(HttpStatus.OK.value(), "success", contents);

    }
    @DeleteMapping(value = "/deleteContent/{id}")
    public Response deleteContent(@PathVariable Integer id){
        Optional<Content> content = contentRespositories.findById(id);
        if (content.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại dữ liệu", null);
        }
        contentRespositories.deleteById(id);
        return new Response(HttpStatus.OK.value(), "success", null);
    }


}
