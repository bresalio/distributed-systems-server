package service;

import java.util.Collection;

import model.Film;
import model.OmdbFilm;
import model.Opinion;
import model.Rating;

public interface ServiceInterface {

	Collection<Film> getAllFilms();
	Collection<Film> getAllFilmsOfGenre(String genreName);
	Collection<Film> getAllFilmsOfDirector(String directorName);
	Collection<Film> getAllFilmsOfActor(String actorName);
	Collection<Film> getAllFilmsBetweenLength(int minLengthInclusive, int maxLengthInclusive);
	Film getFilmData(String title);
	boolean addRating(String filmTitle, Opinion opinion);
	Collection<Rating> getRatingsOfFilm(String filmTitle);
	Opinion getAverageRatingOfFilm(String filmTitle);
	Film convertOmdbFilm(OmdbFilm omdbFilm);
	
}