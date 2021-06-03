package com.fe2.project.rest.repositories.DTO;

public class Login {
    private String email;
    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }
    private String password;
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
