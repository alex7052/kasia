package com.blog.app.task.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.app.task.model.User;
import com.blog.app.task.repository.UserRepository;
import com.blog.app.task.service.UserService;

/**
 *
 * CLASS USER SERVICE
 *
 ****/
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public void register(User user) {
		
		if(validateMail(user.getEmail()) == false){
			throw new IllegalArgumentException("Email no cumple con la validacion");
		}
		
		User u = userRepository.findFirstByEmailOrFirstNameAndLastName(user.getEmail(), user.getFirstName(), user.getLastName());
		if(u != null) {
			throw new IllegalArgumentException("usuario ya existe");
		}
		
		user.setCreatedAt(new Timestamp(new Date().getTime()));
		user.setUpdatedAt(new Timestamp(new Date().getTime()));
		userRepository.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<User> findAllUsers(int page, int size) {

		PageRequest paging = PageRequest.of(page, size);

		return userRepository.findAll(paging);
	}

	@Override
	@Transactional(readOnly = true)
	public User findUserByMail(String email) {
		return userRepository.findByEmail(email);
	}

	private boolean validateMail(String mail) {
		// Patrón para validar el email
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		Matcher mather = pattern.matcher(mail);

		if (mather.find() == true) {
			return true;
		} else {
			return false;
		}
	}

}
