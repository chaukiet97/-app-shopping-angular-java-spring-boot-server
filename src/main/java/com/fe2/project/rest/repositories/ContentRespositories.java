package com.fe2.project.rest.repositories;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.fe2.project.rest.entities.Content;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContentRespositories extends PagingAndSortingRepository<Content, Integer> {
    List<Content>findAll();
    List<Content> findByName(String name);
    
    @Modifying
    @Query(value = "UPDATE `content` SET `create_time`=?1,`description`=?2,`detail`=?3,`group_id`=?4,`images`=?5,"
            + "`link`=?6,`name`=?7,`status`=?8 WHERE `id`=?9", nativeQuery = true)
    @Transactional
    void updateContent(Date create_time, String description, String detail, Integer group_id, String images,
            String link, String name, Integer status, Integer id);
}
