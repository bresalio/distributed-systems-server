package dao;

import java.util.Collection;

import modelForDao.DaoFilm;
import modelForDao.DaoOpinion;
import modelForDao.DaoRating;

public interface DaoInterface {
	
	Collection<DaoFilm> getAllFilms();
	Collection<DaoFilm> getAllFilmsOfGenre(String genreName);
	Collection<DaoFilm> getAllFilmsOfDirector(String directorName);
	Collection<DaoFilm> getAllFilmsOfActor(String actorName);
	Collection<DaoFilm> getAllFilmsBetweenLength(int minLengthInclusive, int maxLengthInclusive);
	DaoFilm getFilmData(String title);
	boolean addRating(String filmTitle, DaoOpinion daoOpinion);
	Collection<DaoRating> getRatingsOfFilm(String filmTitle);
	DaoOpinion getAverageRatingOfFilm(String filmTitle);

}