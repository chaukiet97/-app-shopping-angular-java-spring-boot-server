package com.casso.admintool.rest.repositories;

import com.casso.admintool.rest.entities.User;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    User findByEmail(String email);
    
}
