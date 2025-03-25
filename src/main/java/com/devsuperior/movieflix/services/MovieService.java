package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	
	@Transactional(readOnly = true)
	public MovieDetailsDTO findById(@PathVariable Long id) {
		Movie dto = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id não encontrado"));
		return new MovieDetailsDTO(dto);
		
	}
	
	
	@Transactional(readOnly = true)
	public Page<MovieCardDTO> findAll(Pageable pageable, String genreId) {
		
		Long genreIdRecebido = null;
		if(!"0".equals(genreId)) {
			genreIdRecebido = Long.parseLong(genreId);
		}
		
		Page<Movie> result = movieRepository.searchMovieWhithGenre(pageable,genreIdRecebido);
		return result.map(x -> new MovieCardDTO(x));
	}

}
