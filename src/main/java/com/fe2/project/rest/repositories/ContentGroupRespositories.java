package com.fe2.project.rest.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

import javax.transaction.Transactional;

import com.fe2.project.rest.entities.ContentGroup;

public interface ContentGroupRespositories extends PagingAndSortingRepository<ContentGroup, Integer> {
    List<ContentGroup> findAll();

    @Modifying
    @Query(value = "UPDATE `content_group` SET `link`=?1,`name`=?2 WHERE `id`=?3", nativeQuery = true)
    @Transactional
    void updateContentGroup(String link, String name, Integer id);
}
