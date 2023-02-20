package com.blog.app.task.service;


import java.util.Collection;

import org.springframework.stereotype.Service;

import com.blog.app.task.model.Post;
import com.blog.app.task.model.User;

/***
 *
 *  TODO:
 *
 * **/
@Service
public interface PostService {

    /**
     *
     * Create a post with a given user id in the request
     *
     * **/
    void createPost(int userId, String title, String  body);


    /***
     *
     * Get a post's information and register a visit to it
     *
     * **/
    Post visit(int postId);

    /**
     *
     * Get all posts made by a given user (paginated)
     *
     * **/
    Collection<Post> getAllByUser(int userId,int page, int size);

    /**
     * Add a comment to a post
     */
    void addComment(int userId, int postId, String content);

}
