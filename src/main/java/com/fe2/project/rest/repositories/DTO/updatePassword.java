package com.fe2.project.rest.repositories.DTO;

public class updatePassword {
    private String password_hash;

    public updatePassword(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getPassword_hash() {
        return password_hash;
    }
    
    public updatePassword() {
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }
    
}
