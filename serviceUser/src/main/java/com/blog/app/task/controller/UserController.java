package com.blog.app.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blog.app.task.model.User;
import com.blog.app.task.service.UserService;
import com.blog.app.task.util.Response;
import com.blog.app.task.util.Status;
import com.google.gson.Gson;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * USER CONTROLLER
 *
 * */
@RestController
@RequestMapping("/user")
public class UserController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
    /**
     *
     * Register a user
     *
     * **/
	@PostMapping("/create")
	public ResponseEntity<Response> createUser(HttpServletRequest request, @RequestBody User u){
 		
		log.info("Ingreso al metodo createUser");
		Response r = new Response();
		try {
 			
 	 		userService.register(u);
 	 		
 	 		
 		}catch (Exception e) {
 			e.printStackTrace();
 			log.error("Error creando usuario: " + e.getMessage() );
 			r.setStatusCode(Status.FAIL.getValue());
 			r.setMessage(e.getMessage());
 			r.setResquestUri(request.getRequestURI());
 			r.setParameters(new Gson().toJson(u));
 			return new ResponseEntity<Response>(r, HttpStatus.BAD_REQUEST);
		}
		
		log.info("Se creo usuario con id: " + u.getId());
 		r.setStatusCode(Status.OK.getValue());
		r.setMessage("Se creo usuario con id: " + u.getId());
		r.setResquestUri(request.getRequestURI());
		r.setParameters(new Gson().toJson(u));
		return new ResponseEntity<Response>(r, HttpStatus.CREATED);

	}
	

    /**
     *
     * Get all users (paginated)
     *
     * **/
	
	@PostMapping("/list")
	public ResponseEntity<Response> listUser(HttpServletRequest request,
			                                  @RequestParam (name = "size", required = true) int size,
			                                  @RequestParam (name = "page", required = true) int page ){
 		
		log.info("Ingreso al metodo listUser");
		Response r = new Response();
		List<User> result = null;
		try {
 			
 	 	result = userService.findAllUsers(page, size).getContent();
 	 		
 	 		
 		}catch (Exception e) {
 			e.printStackTrace();
 			log.error("Error cosultando usuario: " + e.getMessage() );
 			r.setStatusCode(Status.FAIL.getValue());
 			r.setMessage(e.getMessage());
 			r.setResquestUri(request.getRequestURI());
 			r.setParameters("page: " + page + "size: "  + size );
 			r.setPageInformation("page: " + page + " size: "  + size );
 			return new ResponseEntity<Response>(r, HttpStatus.BAD_REQUEST);
		}
		
		log.info("Se consulto usuarios: page: " + page + "size: "  + size);
 		r.setStatusCode(Status.OK.getValue());
		r.setMessage("Consulta Exitosa");
		r.setResquestUri(request.getRequestURI());
		r.setParameters("page: " + page + " size: "  + size );
		r.setPageInformation("page: " + page + " size: "  + size );
		r.setContent(result);
		return new ResponseEntity<Response>(r, HttpStatus.OK);

	}
	
	

	/**
	 * Find a user by email
	 */
	
	@PostMapping("/getMail")
	public ResponseEntity<Response> getMail(HttpServletRequest request,
			                                  @RequestParam (name = "mail", required = true) String mail ){
 		
		log.info("Ingreso al metodo getMail");
		Response r = new Response();
		User result = null;
		try {
 			
 	     result = userService.findUserByMail(mail);
 	 		
 	 		
 		}catch (Exception e) {
 			e.printStackTrace();
 			log.error("Error cosultando usuario: " + e.getMessage() );
 			r.setStatusCode(Status.FAIL.getValue());
 			r.setMessage(e.getMessage());
 			r.setResquestUri(request.getRequestURI());
 			r.setParameters(mail);
 			return new ResponseEntity<Response>(r, HttpStatus.BAD_REQUEST);
		}
		
		log.info("Se consulto usuario: mail: " + mail);
 		r.setStatusCode(Status.OK.getValue());
		r.setMessage("Consulta Exitosa");
		r.setResquestUri(request.getRequestURI());
		r.setParameters(mail );
		r.setContent(result);
		return new ResponseEntity<Response>(r, HttpStatus.OK);

	}
	

}
