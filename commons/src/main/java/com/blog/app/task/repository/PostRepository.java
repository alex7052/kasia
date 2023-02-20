package com.blog.app.task.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.blog.app.task.model.Post;
import com.blog.app.task.model.User;

public interface PostRepository extends PagingAndSortingRepository<Post, Integer> {

	List<Post> findByUser(User u, Pageable pageable);

}
