package com.casso.admintool.rest.controllers;

import java.util.List;

import com.casso.admintool.rest.entities.User;
import com.casso.admintool.rest.repositories.UserRepository;
import com.casso.admintool.rest.repositories.DTO.Login;
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

    @PostMapping("/login")
    public Response Login(@RequestBody Login login) {
        User user = userRepository.findByEmail(login.getEmail());
        if (user == null) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Email không tồn tại", null);
        }

        String passwordHashServer = user.getPassword_hash();
        String passwordHashClient = login.getPassword();
        passwordHashClient = SHA512.encryptThisString(passwordHashClient);
        if (!passwordHashServer.equals(passwordHashClient)) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Email hoặc mật khẩu không chính xác", null);
        }
        return new Response(HttpStatus.OK.value(), "success", user);
    }

    @GetMapping(value = "/getAllUser")
    public Response getAllUser() {
        List<User> user = userRepository.findAll();
        if (user.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "User rỗng", null);
        }
        for (int i = 0; i < user.size(); i++) {
            if (!user.get(i).getPassword_hash().isEmpty()) {
                user.get(i).setPassword_hash("");
            }
        }
        return new Response(HttpStatus.OK.value(), "success", user);
    }

}
