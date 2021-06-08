package com.fe2.project.rest.repositories;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.fe2.project.rest.entities.User;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    // User findByEmail(String email);

    List<User> findByEmail(String email);

    List<User> findAll();

    // update user
    @Modifying
    @Query(value = "UPDATE `user` SET `brithday`=?1,`email`=?2,`firstname`=?3,`lastname`=?4,`name`=?5,"
            + "`phone`=?6,`sex`=?7,`avatar`=?8,`status`=?9 WHERE `id`=?10", nativeQuery = true)
    @Transactional
    void updateUser(Date brithday, String email, String firstname, String lastname, String name, String phone,
            Integer sex, String avatar, Integer status, Integer id);

    // update password
    @Modifying
    @Query(value = "UPDATE `user` SET `password_hash`=?1 WHERE `id`=?2", nativeQuery = true)
    @Transactional
    void updatePasswordhash(String password_hash, Integer id);

}
