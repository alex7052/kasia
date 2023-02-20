package com.blog.app.task.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.task.model.Post;
import com.blog.app.task.service.PostService;
import com.blog.app.task.util.Response;
import com.blog.app.task.util.Status;

/***
 *
 *
 *  MATCH CONTROLLER
 *
 *
 * ***/
@RestController
@RequestMapping("/posts")
public class PostController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	

	@Autowired
	private PostService postService;
    /**
     *
     * Create a post with a given user id in the request
     *
     * **/
	@PostMapping("/create")
	public ResponseEntity<Response> createpost(HttpServletRequest request,  
												 @RequestParam (name = "userId", required = true) int userId,
												 @RequestParam (name = "title", required = true) String title,
												 @RequestParam (name = "body", required = true) String body){
 		
		log.info("Ingreso al metodo createpost");
		Response r = new Response();
		try {
 			
			postService.createPost(userId,title, body);
 	 		
 	 		
 		}catch (Exception e) {
 			e.printStackTrace();
 			log.error("Error creando post: " + e.getMessage() );
 			r.setStatusCode(Status.FAIL.getValue());
 			r.setMessage(e.getMessage());
 			r.setResquestUri(request.getRequestURI());
 			r.setParameters("UserId: " + userId);
 			return new ResponseEntity<Response>(r, HttpStatus.BAD_REQUEST);
		}
		
		log.info("Se creo post");
 		r.setStatusCode(Status.OK.getValue());
		r.setMessage("Se creo post");
		r.setResquestUri(request.getRequestURI());
		r.setParameters("UserId: " + userId);
		return new ResponseEntity<Response>(r, HttpStatus.CREATED);

	}
	

    /***
     *
     * Get a post's information and register a visit to it
     *
     * **/
	
	@PostMapping("/visit")
	public ResponseEntity<Response> visit(HttpServletRequest request,  
												 @RequestParam (name = "postId", required = true) int postId){
 		
		log.info("Ingreso al metodo visit");
		Response r = new Response();
		Post p = null;
		try {
 			
			p = postService.visit(postId);
 	 		
 	 		
 		}catch (Exception e) {
 			e.printStackTrace();
 			log.error("Error visitando post: " + e.getMessage() );
 			r.setStatusCode(Status.FAIL.getValue());
 			r.setMessage(e.getMessage());
 			r.setResquestUri(request.getRequestURI());
 			r.setParameters("postId: " + postId);
 			return new ResponseEntity<Response>(r, HttpStatus.BAD_REQUEST);
		}
		
		log.info("Se visito post");
 		r.setStatusCode(Status.OK.getValue());
		r.setMessage("Se visito post");
		r.setResquestUri(request.getRequestURI());
		r.setParameters("postId: " + postId);
		r.setContent(p);
		return new ResponseEntity<Response>(r, HttpStatus.OK);

	}
	

    /**
     *
     * Get all posts made by a given user (paginated)
     *
     * **/
	
	@PostMapping("/getByUser")
	public ResponseEntity<Response> getByUser(HttpServletRequest request,  
												 @RequestParam (name = "userId", required = true) int userId,
												 @RequestParam (name = "size", required = true) int size,
				                                 @RequestParam (name = "page", required = true) int page){
 		
		log.info("Ingreso al metodo getByUser");
		Response r = new Response();
		List<Post> p = null;
		try {
 			
			p = (List<Post>) postService.getAllByUser(userId, page, size);
 	 		
 	 		
 		}catch (Exception e) {
 			e.printStackTrace();
 			log.error("Error consulto post: " + e.getMessage() );
 			r.setStatusCode(Status.FAIL.getValue());
 			r.setMessage(e.getMessage());
 			r.setResquestUri(request.getRequestURI());
 			r.setParameters("UserId:" + userId + " page: " + page + " size: "  + size);
 			return new ResponseEntity<Response>(r, HttpStatus.BAD_REQUEST);
		}
		
		log.info("Se consulto post");
 		r.setStatusCode(Status.OK.getValue());
		r.setMessage("Se consulto post");
		r.setResquestUri(request.getRequestURI());
		r.setParameters("UserId:" + userId + " page: " + page + " size: "  + size);
		r.setPageInformation(" page: " + page + " size: "  + size);
		r.setContent(p);
		return new ResponseEntity<Response>(r, HttpStatus.OK);

	}

	/**
	 * Add a comment to a post
	 */
	
	@PostMapping("/createComment")
	public ResponseEntity<Response> createcomment(HttpServletRequest request,  
												 @RequestParam (name = "userId", required = true) int userId,
												 @RequestParam (name = "postId", required = true) int postId,
												 @RequestParam (name = "content", required = true) String content){
 		
		log.info("Ingreso al metodo createcomment");
		Response r = new Response();
		try {
 			
			postService.addComment(userId, postId, content);
 	 		
 	 		
 		}catch (Exception e) {
 			e.printStackTrace();
 			log.error("Error creando comentario: " + e.getMessage() );
 			r.setStatusCode(Status.FAIL.getValue());
 			r.setMessage(e.getMessage());
 			r.setResquestUri(request.getRequestURI());
 			r.setParameters("UserId: " + userId + "postId: " + postId);
 			return new ResponseEntity<Response>(r, HttpStatus.BAD_REQUEST);
		}
		
		log.info("Se creo comentario");
 		r.setStatusCode(Status.OK.getValue());
		r.setMessage("Se creo comentario");
		r.setResquestUri(request.getRequestURI());
		r.setParameters("UserId: " + userId + "postId: " + postId);
		return new ResponseEntity<Response>(r, HttpStatus.CREATED);

	}
	

}
