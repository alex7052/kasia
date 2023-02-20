package com.blog.app.task.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.blog.app.task.model.Comment;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Integer> {

}
