package com.fe2.project.rest.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fe2.project.rest.entities.User;
import com.fe2.project.rest.repositories.UserRepository;
import com.fe2.project.rest.repositories.DTO.Login;
import com.fe2.project.rest.repositories.DTO.Response;
import com.fe2.project.rest.repositories.DTO.updatePassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.fe2.util.SHA512;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public Response Register(@RequestBody User user) {
        List<User> resultsuser = userRepository.findByEmail(user.getEmail());
        if (resultsuser.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Đã tồn tại email", null);
        }
        String password = user.getPassword_hash();
        user.setPassword_hash(SHA512.encryptThisString(password));
        user.setAvatar("");
        user.setStatus(1);
        userRepository.save(user);
        return new Response(HttpStatus.OK.value(), "success", user);
    }

    @PostMapping("/login")
    public Response Login(@RequestBody Login login) {
        List<User> user = userRepository.findByEmail(login.getEmail());
        if (user.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Email không tồn tại", null);
        }

        String passwordHashServer = user.get(0).getPassword_hash();
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
            if (user.get(i).getPassword_hash() !=null) {
                user.get(i).setPassword_hash("");
            }
        }
        return new Response(HttpStatus.OK.value(), "success", user);
    }

    @GetMapping(value = "/getUser/{userId}")
    public Response getMethodName(@PathVariable Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.get().getId().equals(userId)) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại ID", null);
        }
        return new Response(HttpStatus.OK.value(), "success", user);
    }

    @PostMapping(value = "/insertUser")
    public Response insertUser(@RequestBody User userBody) {
        List<User> user = userRepository.findByEmail(userBody.getEmail());
        // System.out.println(user.getEmail());
        for (int i = 0; i < user.size(); i++) {
            if (!user.get(i).getEmail().equals(userBody.getEmail())) {
                return new Response(HttpStatus.NOT_FOUND.value(), "Email đã tồn tại", null);
            }
        }
        // if (user.get(0) != null) {
        //     return new Response(HttpStatus.NOT_FOUND.value(), "Email đã tồn tại", null);
        // }
        userBody.setCreate_time(new Date());
        userBody.setName(userBody.getFirstname() + " " + userBody.getLastname());
        userRepository.save(userBody);
        return new Response(HttpStatus.OK.value(), "success", null);
    }

    @PostMapping(value = "/updateUser/{id}")
    public Response updateUser(@RequestBody User user, @PathVariable Integer id) {
        Optional<User> optional = userRepository.findById(id);
        if (!optional.get().getId().equals(id)) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại ID", null);
        }
        user.setName(user.getFirstname() + " " + user.getLastname());
        userRepository.updateUser(user.getBrithday(), user.getEmail(), user.getFirstname(), user.getLastname(),
                user.getName(), user.getPhone(), user.getSex(), user.getAvatar(), user.getStatus(), id);
        return new Response(HttpStatus.OK.value(), "success", null);
    }

    @PostMapping(value = "/updatePassword/{id}")
    public Response updatePassword(@RequestBody updatePassword updatePassword, @PathVariable Integer id) {
        Optional<User> optional = userRepository.findById(id);
        if (!optional.get().getId().equals(id)) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại ID", null);
        }
        String passwordHash = updatePassword.getPassword_hash();
        passwordHash = SHA512.encryptThisString(passwordHash);
        userRepository.updatePasswordhash(passwordHash, id);
        return new Response(HttpStatus.OK.value(), "success", null);
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public Response deleteUserById(@PathVariable Integer id) {
        Optional<User> optional = userRepository.findById(id);
        if (!optional.get().getId().equals(id)) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại ID", null);
        }
        userRepository.deleteById(id);
        return new Response(HttpStatus.OK.value(), "success", null);
    }

}
