package com.member_favor.model;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.genre.model.GenreDAO;
import com.genre.model.GenreDAO_interface;
import com.movie.model.MovieDAO;
import com.movie.model.MovieDAO_interface;
import com.movie.model.MovieVO;
import com.movie_genre.model.Movie_GenreDAO;
import com.movie_genre.model.Movie_GenreDAO_interface;

public class MemberFavorService {
	private MemberFavorDAO_interface dao;
	private GenreDAO_interface genredao;
	private Movie_GenreDAO_interface mgdao;
	private MovieDAO_interface moviedao;
	public MemberFavorService() {
		dao = new MemberFavorDAO();
		genredao = new GenreDAO();
		mgdao = new Movie_GenreDAO();
		moviedao = new MovieDAO();
	}
	
	public void add(MemberFavorVO newmemberfavor) {

	}	
	
	public void delete(Integer member_id, Integer genre_id) {
		dao.delete(member_id);
	}
	
	public List<MemberFavorVO> getOneMemFavor(Integer member_id) {
		return dao.getOneMemFavor(member_id);
	}
	
	public Set<MovieVO> getRecommendMovieByMemFavor(Integer member_id){
		List<MemberFavorVO> memberfavor = dao.getOneMemFavor(member_id);
		Set<Set<Long>> genresInOneMem = new LinkedHashSet<Set<Long>>();
		Set<MovieVO> recommendmovie = new HashSet<MovieVO>();
		for(MemberFavorVO mfv : memberfavor){
			genredao.findByPrimaryKey(mfv.getGenre_id());
			genresInOneMem.add(mgdao.getMovieByGenre(mfv.getGenre_id()));
		}
		for(Set<Long> set : genresInOneMem){
			for(Long movie_id : set){
				recommendmovie.add(moviedao.findByPrimaryKey(movie_id));
			}
		}
		return recommendmovie;		
	}
	
	public boolean checkedFavor(Integer member_id, Integer genre_id){		
		if(dao.getFavor(member_id, genre_id) == null){
			return false;
		}else{
			return true;
		}
	}
}
