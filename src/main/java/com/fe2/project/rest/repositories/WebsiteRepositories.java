package com.fe2.project.rest.repositories;

import javax.transaction.Transactional;

import com.fe2.project.rest.entities.Website;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.*;

public interface WebsiteRepositories extends PagingAndSortingRepository<Website,Integer>{
    @Modifying
    @Query(value = "UPDATE `website` SET `description`=?1,`logo`=?2,`shortcut`=?3,`title`=?4 WHERE `id` =?5", nativeQuery = true)
    @Transactional
    void updateWebsite(String description, String logo, String shortcut, String title, Integer id);
}
