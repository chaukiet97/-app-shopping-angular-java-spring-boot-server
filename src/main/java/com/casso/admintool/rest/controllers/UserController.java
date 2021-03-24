package com.casso.admintool.rest.controllers;
import com.casso.admintool.rest.entities.User;
import com.casso.admintool.rest.repositories.UserRepository;
import com.casso.admintool.rest.repositories.DTO.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.casso.util.SHA512;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public Response Register(@RequestBody User user) {
        User resultsuser = userRepository.findByEmail(user.getEmail());
        if (resultsuser.getEmail().equals(user.getEmail())) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Đã tồn tại email", null);
        }
        String password = user.getPassword_hash();
        user.setPassword_hash(SHA512.encryptThisString(password));
        userRepository.save(user);
        return new Response(HttpStatus.OK.value(), "success", user);
    }

}
