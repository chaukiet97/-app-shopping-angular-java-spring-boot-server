package com.fe2.project.rest.repositories;

import java.util.List;
import java.util.Optional;

import com.fe2.project.rest.entities.PagesGroup;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PagesGroupsRepositories extends PagingAndSortingRepository<PagesGroup, Integer> {
    List<PagesGroup> findByName(String name);

    Optional<PagesGroup> findById(Integer id);

    List<PagesGroup> findAll();

    @Query(value = "SELECT * FROM pages_group WHERE id =?1", nativeQuery = true)
    List<PagesGroup> findPagesGroupById(Integer id);

    @Modifying
    @Query(value = "UPDATE `pages_group` SET `name`=?1 WHERE id = ?2", nativeQuery = true)
    @Transactional
    void updatePagesGroup(String name, Integer id);

    @Modifying
    @Query(value = "UDELETE FROM `pages_group` WHERE id = ?1", nativeQuery = true)
    @Transactional
    void deletePagesGroup(Integer id);
}
