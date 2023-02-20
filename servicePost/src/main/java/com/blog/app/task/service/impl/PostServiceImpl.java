package com.blog.app.task.service.impl;

import com.blog.app.task.model.Comment;
import com.blog.app.task.model.Post;
import com.blog.app.task.model.User;
import com.blog.app.task.repository.CommentRepository;
import com.blog.app.task.repository.PostRepository;
import com.blog.app.task.repository.UserRepository;
import com.blog.app.task.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
    private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CommentRepository commentRepository;


	@Override
	@Transactional
    public void createPost(int userId, String title, String body) {
    	
    	Optional<User> u = userRepository.findById(userId);
		if(u.isEmpty()) {
			throw new IllegalArgumentException("usuario no existe");
		}
    	
    	Post p = new Post();
    	p.setBody(body);
    	p.setTitle(title);
    	p.setCreatedAt(new Timestamp(new Date().getTime()));
    	p.setUpdatedAt(new Timestamp(new Date().getTime()));
    	p.setViews(0);
    	p.setUser(u.get());
    	postRepository.save(p);
    	
    }

    @Override
	@Transactional
    public Post visit(int postId) {
    	
    	Optional<Post> p = postRepository.findById(postId);
    	if(p.isEmpty()) {
			throw new IllegalArgumentException("post no existe");
		}
    	
    	p.get().setViews(p.get().getViews()+1);
    	p.get().setUpdatedAt(new Timestamp(new Date().getTime()));
    	postRepository.save(p.get());
    	
        return p.get() ;
    }

    @Override
	@Transactional(readOnly = true)
    public Collection<Post> getAllByUser(int userId,int page, int size) {
        
    	PageRequest paging = PageRequest.of(page, size);
    	Optional<User> u = userRepository.findById(userId);
		if(u.isEmpty()) {
			throw new IllegalArgumentException("usuario no existe");
		}
    	
		return postRepository.findByUser(u.get(), paging);
    	
    	
    }

    @Override
    public void addComment(int userId, int postId, String content) {

    	Optional<User> u = userRepository.findById(userId);
		if(u.isEmpty()) {
			throw new IllegalArgumentException("usuario no existe");
		}
		
		
    	Optional<Post> p = postRepository.findById(postId);
		if(p.isEmpty()) {
			throw new IllegalArgumentException("post no existe");
		}
		
		Comment co = new Comment();
		co.setAuthor(u.get());
		co.setPost(p.get());
		co.setBody(content);
		co.setCreatedAt(new Timestamp(new Date().getTime()));
		co.setUpdatedAt(new Timestamp(new Date().getTime()));
		commentRepository.save(co);
		
		
    	
    }


}
