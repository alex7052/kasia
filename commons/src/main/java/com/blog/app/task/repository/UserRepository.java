package com.blog.app.task.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blog.app.task.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

	User findByEmail (String email);
	User findFirstByEmailOrFirstNameAndLastName(@Param("email") String email, @Param("firstName")String firstName, @Param("lastName")String lastName);
}
