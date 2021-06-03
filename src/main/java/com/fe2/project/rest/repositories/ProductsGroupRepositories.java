package com.fe2.project.rest.repositories;

import java.util.List;
import javax.transaction.Transactional;

import com.fe2.project.rest.entities.ProductsGroup;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductsGroupRepositories extends PagingAndSortingRepository<ProductsGroup, Integer> {
    List<ProductsGroup> findByName(String name);

    List<ProductsGroup> findAll();

    @Modifying
    @Query(value = "UPDATE `products_group` SET `name`=?1 WHERE id = ?2", nativeQuery = true)
    @Transactional
    void updateProductGroup(String name, Integer id);
}
