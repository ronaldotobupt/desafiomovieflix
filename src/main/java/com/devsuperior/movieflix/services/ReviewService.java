package com.devsuperior.movieflix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private AuthService authService;
	
	@Transactional(readOnly = true)
	public List<ReviewDTO> searchReviewMovie(@PathVariable Long id) {
		return reviewRepository.searchReviewMovie(id); 
		
	}
	
	
	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {
		
		Movie movie = new Movie();
		movie.setId(dto.getMovieId());
		User user = authService.authenticated();
		
		Review entity = new Review();
		entity.setText(dto.getText());
		entity.setMovie(movie);
		entity.setUser(user);
		
		entity = reviewRepository.save(entity);
		
		return new ReviewDTO(entity);
	}


}
