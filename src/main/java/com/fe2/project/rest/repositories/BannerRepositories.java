package com.fe2.project.rest.repositories;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

import javax.transaction.Transactional;

import com.fe2.project.rest.entities.Banner;

import org.springframework.data.jpa.repository.*;

public interface BannerRepositories extends PagingAndSortingRepository<Banner,Integer> {
    List<Banner> findAll();
    List<Banner> findByName(String name);

    @Modifying
    @Query(value = "UPDATE `banner` SET `images`=?1,`link`=?2,`name`=?3,`status`=?4,`type`=?5,`description`=?6 WHERE `id`= ?7", nativeQuery = true)
    @Transactional
    void updateBanner(String images, String link, String name, Integer status, Integer type,String description, Integer id);

    @Modifying
    @Query(value = "SELECT * FROM `banner` WHERE `type` = 0", nativeQuery = true)
    @Transactional
    List<Banner>getAllSlide();
}
