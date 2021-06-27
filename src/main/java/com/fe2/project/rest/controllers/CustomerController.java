package com.fe2.project.rest.controllers;

import java.util.Date;

import com.fe2.project.rest.entities.Customer;
import com.fe2.project.rest.repositories.CustomerRespositories;
import com.fe2.project.rest.repositories.DTO.Response;
import com.fe2.util.SHA512;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    private CustomerRespositories customerRespositories;

    @PostMapping(value="/insertCustomer")
    public Response insertCustomer(@RequestBody Customer customer) {
        String password = customer.getPassword();
        customer.setPassword(SHA512.encryptThisString(password));
        customer.setStatus(1);
        customer.setCreate_time(new Date());
        customerRespositories.save(customer);
        return new Response(HttpStatus.OK.value(), "success", customer);
    }
    
}
