package com.fe2.project.rest.repositories;

import java.util.List;

import javax.transaction.Transactional;

import com.fe2.project.rest.entities.MadeIn;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MadeInRepositories extends PagingAndSortingRepository<MadeIn,Integer>{
    List<MadeIn> findByName(String name);
    List<MadeIn> findAll();

    @Modifying
    @Query(value = "UPDATE `made_in` SET `name`=?1 WHERE id = ?2", nativeQuery = true)
    @Transactional
    void updateMadeIn(String name, Integer id);
    
}
