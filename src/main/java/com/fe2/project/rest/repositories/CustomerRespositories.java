package com.fe2.project.rest.repositories;

import java.util.List;

import com.fe2.project.rest.entities.Customer;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRespositories extends PagingAndSortingRepository<Customer, Integer> {
    List<Customer> findByEmail(String email);
    List<Customer> findAll();
}
