package com.casso.admintool.rest.repositories;

import java.util.List;

import javax.transaction.Transactional;

import com.casso.admintool.rest.entities.Brand;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BrandRepositories extends PagingAndSortingRepository<Brand,Integer>{
    List<Brand> findByName(String name);
    List<Brand> findAll();

    @Modifying
    @Query(value = "UPDATE `brand` SET `name`=?1 WHERE id = ?2", nativeQuery = true)
    @Transactional
    void updateMadeIn(String name, Integer id);
}
