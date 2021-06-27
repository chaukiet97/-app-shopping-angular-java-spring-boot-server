package com.fe2.project.rest.repositories.DTO;

public class SearchDTO {
    private String link;

    public SearchDTO(String link) {
        this.link = link;
    }

    public SearchDTO() {
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
