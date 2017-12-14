package service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import dao.DaoInterface;
import model.Film;
import model.OmdbFilm;
import model.Opinion;
import model.Rating;

public class ServiceImpl implements ServiceInterface {
	
	@Autowired
	private DaoInterface dao;
	
	public ServiceImpl(DaoInterface dao) {
		this.dao = dao;
	}

	public Collection<Film> getAllFilms() {
		return dao.getAllFilms();
	}

	public Collection<Film> getAllFilmsOfGenre(String genreName) {
		return dao.getAllFilmsOfGenre(genreName);
	}

	public Collection<Film> getAllFilmsOfDirector(String directorName) {
		return dao.getAllFilmsOfDirector(directorName);
	}

	public Collection<Film> getAllFilmsOfActor(String actorName) {
		return dao.getAllFilmsOfActor(actorName);
	}

	public Collection<Film> getAllFilmsBetweenLength(int minLengthInclusive,
			int maxLengthInclusive) {
		return dao.getAllFilmsBetweenLength(minLengthInclusive, maxLengthInclusive);
	}

	public Film getFilmData(String title) {
		return dao.getFilmData(title);
	}

	public boolean addRating(String filmTitle, Opinion opinion) {
		return dao.addRating(filmTitle, opinion);
	}

	public Collection<Rating> getRatingsOfFilm(String filmTitle) {
		return dao.getRatingsOfFilm(filmTitle);
	}

	public Opinion getAverageRatingOfFilm(String filmTitle) {
		return dao.getAverageRatingOfFilm(filmTitle);
	}
	
	public Film convertOmdbFilm(OmdbFilm omdbFilm) {
		return new Film(omdbFilm);
	}

}