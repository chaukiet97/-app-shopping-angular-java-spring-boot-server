package com.fe2.project.rest.repositories;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.fe2.project.rest.entities.Contact;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContactRepositories extends PagingAndSortingRepository<Contact,Integer> {
    List<Contact> findAll();

    @Modifying
    @Query(value = "UPDATE `contact` SET `day_reply`=?1,`maker_id`=?2,`reply`=?3,`status`=?4 WHERE `id`=?5", nativeQuery = true)
    @Transactional
    void replyContact(Date day_reply, Integer maker_id, String reply, Integer status, Integer id);
}
