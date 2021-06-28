package com.fe2.project.rest.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fe2.project.rest.entities.Customer;
import com.fe2.project.rest.repositories.CustomerRespositories;
import com.fe2.project.rest.repositories.DTO.Response;
import com.fe2.util.SHA512;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



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
    @GetMapping(value="/getAllCustomer")
    public Response getAllCustomer() {
        List<Customer> customer = customerRespositories.findAll();
        if (customer.isEmpty()) {
        return new Response(HttpStatus.OK.value(), "Không tồn tại khách hàng", null);
        }
        return new Response(HttpStatus.OK.value(), "success", customer);
    }
    @DeleteMapping(value = "/deleteCustomer/{id}")
    public Response deleteCustomer(@PathVariable Integer id){
        Optional <Customer> optional = customerRespositories.findById(id);
        if (!optional.isEmpty()) {
        return new Response(HttpStatus.OK.value(), "Không tồn tại khách hàng",null);
        }
        customerRespositories.deleteById(id);
        return new Response(HttpStatus.OK.value(), "success", null);
    }
    
    
}
