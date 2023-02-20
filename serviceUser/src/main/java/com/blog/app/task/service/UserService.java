package com.blog.app.task.service;

import com.blog.app.task.model.User;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface UserService {

    /**
     *
     * Register a user
     *
     * **/
    void register(User user);

    /**
     *
     * Get all users (paginated)
     *
     * **/
    Page<User> findAllUsers(int page, int size);

    /**
     * Find a user by email
     */
    User findUserByMail(String email);

}
