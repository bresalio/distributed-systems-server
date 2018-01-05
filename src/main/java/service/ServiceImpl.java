package service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import dao.DaoInterface;
import modelForControllerIn.OmdbFilm;
import modelForControllerOut.Film;
import modelForControllerOut.Opinion;
import modelForControllerOut.Rating;
import modelForDao.DaoFilm;
import modelForDao.DaoOpinion;
import modelForDao.DaoRating;

public class ServiceImpl implements ServiceInterface {
	
	@Autowired
	private DaoInterface dao;
	
	public ServiceImpl(DaoInterface dao) {
		this.dao = dao;
	}

	public Collection<Film> getAllFilms() {
		Collection<Film> result = new ArrayList<>();
		
		Collection<DaoFilm> films = dao.getAllFilms();
		for (DaoFilm film : films) {
			result.add(new Film(film.getTitle(), film.getGenre(), film.getDirector(),
					film.getMainActors(), film.getMinutes(), film.getPublicationYear()));
		}
		
		return result;
	}

	public Collection<Film> getAllFilmsOfGenre(String genreName) {
		Collection<Film> result = new ArrayList<>();
		
		Collection<DaoFilm> films = dao.getAllFilmsOfGenre(genreName);
		for (DaoFilm film : films) {
			result.add(new Film(film.getTitle(), film.getGenre(), film.getDirector(),
					film.getMainActors(), film.getMinutes(), film.getPublicationYear()));
		}
		
		return result;
	}

	public Collection<Film> getAllFilmsOfDirector(String directorName) {
		Collection<Film> result = new ArrayList<>();
		
		Collection<DaoFilm> films = dao.getAllFilmsOfDirector(directorName);
		for (DaoFilm film : films) {
			result.add(new Film(film.getTitle(), film.getGenre(), film.getDirector(),
					film.getMainActors(), film.getMinutes(), film.getPublicationYear()));
		}
		
		return result;
	}

	public Collection<Film> getAllFilmsOfActor(String actorName) {
		Collection<Film> result = new ArrayList<>();
		
		Collection<DaoFilm> films = dao.getAllFilmsOfActor(actorName);
		for (DaoFilm film : films) {
			result.add(new Film(film.getTitle(), film.getGenre(), film.getDirector(),
					film.getMainActors(), film.getMinutes(), film.getPublicationYear()));
		}
		
		return result;
	}

	public Collection<Film> getAllFilmsBetweenLength(int minLengthInclusive,
			int maxLengthInclusive) {
		
		
		Collection<Film> result = new ArrayList<>();
		
		Collection<DaoFilm> films = dao.getAllFilmsBetweenLength(minLengthInclusive, maxLengthInclusive);
		for (DaoFilm film : films) {
			result.add(new Film(film.getTitle(), film.getGenre(), film.getDirector(),
					film.getMainActors(), film.getMinutes(), film.getPublicationYear()));
		}
		
		return result;
	}

	public Film getFilmData(String title) {
		DaoFilm film = dao.getFilmData(title);
		return new Film(film.getTitle(), film.getGenre(), film.getDirector(),
				film.getMainActors(), film.getMinutes(), film.getPublicationYear());
	}

	public boolean addRating(String filmTitle, Opinion opinion) {
		DaoOpinion daoOpinion = switchOpinion(opinion);
		return dao.addRating(filmTitle, daoOpinion);
	}

	public Collection<Rating> getRatingsOfFilm(String filmTitle) {
		Collection<Rating> result = new ArrayList<>();
		
		Collection<DaoRating> ratings = dao.getRatingsOfFilm(filmTitle);
		for (DaoRating rating : ratings) {
			Film film = new Film(rating.getFilm().getTitle(), rating.getFilm().getGenre(), rating.getFilm().getDirector(),
					rating.getFilm().getMainActors(), rating.getFilm().getMinutes(), rating.getFilm().getPublicationYear());
			Opinion opinion = switchDaoOpinion(rating.getOpinion());
			result.add(new Rating(film, opinion));
		}
		
		return result;
	}

	public Opinion getAverageRatingOfFilm(String filmTitle) {
		return switchDaoOpinion(dao.getAverageRatingOfFilm(filmTitle));
	}
	
	public Film convertOmdbFilm(OmdbFilm omdbFilm) {
		return new Film(omdbFilm);
	}
	
	public Film emptyFilmData() {
		return new Film();
	}
	
	private DaoOpinion switchOpinion(Opinion opinion) {
		switch (opinion) {
		case VERY_BAD: {
			return DaoOpinion.VERY_BAD;
		}
		case RATHER_BAD: {
			return DaoOpinion.RATHER_BAD;
		}
		case NEUTRAL: {
			return DaoOpinion.NEUTRAL;
		}
		case RATHER_GOOD: {
			return DaoOpinion.RATHER_GOOD;
		}
		case VERY_GOOD: {
			return DaoOpinion.VERY_GOOD;
		}
		default: {
			return DaoOpinion.NOT_FOUND;
		}
		}
	}
	
	private Opinion switchDaoOpinion(DaoOpinion daoOpinion) {
		switch (daoOpinion) {
		case VERY_BAD: {
			return Opinion.VERY_BAD;
		}
		case RATHER_BAD: {
			return Opinion.RATHER_BAD;
		}
		case NEUTRAL: {
			return Opinion.NEUTRAL;
		}
		case RATHER_GOOD: {
			return Opinion.RATHER_GOOD;
		}
		case VERY_GOOD: {
			return Opinion.VERY_GOOD;
		}
		default: {
			return Opinion.NOT_FOUND;
		}
		}
	}

}