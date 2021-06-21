package com.fe2.project.rest.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.fe2.project.rest.entities.Page;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PageRepositories extends PagingAndSortingRepository<Page, Integer> {
        List<Page> findByName(String name);

        Optional<Page> findById(Integer id);

        List<Page> findAll();

        @Modifying
        @Query(value = "UPDATE `page` SET `description`=?1,`detail`=?2,`group_id`=?3,`link`=?4,"
                        + "`name`=?5,`status`=?6,`type`=?7 WHERE `id`=?8", nativeQuery = true)
        @Transactional
        void updatePage(String description, String detail, Integer group_id, String link, String name, Integer status,
                        Integer type, Integer id);

        @Query(value = "SELECT * FROM `page` WHERE type=3", nativeQuery = true)
        List<Page> getPageByType();

        @Query(value = "SELECT * FROM `page` WHERE group_id=?1", nativeQuery = true)
        List<Page>menu(Integer id);
}
