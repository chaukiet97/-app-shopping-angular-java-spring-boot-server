package com.fe2.project.rest.repositories.DTO;

public class Response {
    private Integer error;
    private String message;
    private Object data;

    public Response () {}

    public Response (Integer error, String message, Object data) {
        this.error = error;
        this.message = message;
        this.data = data;
    } 

    public Integer getError(){
        return error;
    }
    
    public String getMessage(){
        return message;
    }

    public Object getData(){
        return data;
    }

    public void setError(Integer error) {
        this.error = error;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setData(Object data) {
        this.data = data;
    }

}
