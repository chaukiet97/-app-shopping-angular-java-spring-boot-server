package com.casso.admintool.rest.repositories;

import java.util.List;

import com.casso.admintool.rest.entities.ProductsGroup;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductsRepositories extends PagingAndSortingRepository<ProductsGroup, Integer> {
    List<ProductsGroup> findByName(String name);

    List<ProductsGroup> findAll();

}
