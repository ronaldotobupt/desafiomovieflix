package com.devsuperior.movieflix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	@Query(value = """
			SELECT obj FROM Review obj JOIN FETCH obj.movie WHERE obj.movie.id = :IdMovie
			""")
	List<ReviewDTO>searchReviewMovie(Long IdMovie);

}
