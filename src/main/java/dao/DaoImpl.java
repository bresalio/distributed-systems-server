package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelForDao.DaoFilm;
import modelForDao.DaoOpinion;
import modelForDao.DaoRating;

public class DaoImpl implements DaoInterface {
	
	private Collection<DaoFilm> daoFilms = new ArrayList<DaoFilm>();
	private Collection<DaoRating> daoRatings = new ArrayList<DaoRating>();
	
	private Map<DaoOpinion, Integer> opinionsAsIntValues = new HashMap<DaoOpinion, Integer>();
	
	public DaoImpl() {
		daoFilms.add(new DaoFilm("Once Upon a Time in the West", "western", "Sergio Leone", new String[] {"Claudia Cardinale", "Henry Fonda", "Jason Robards", "Charles Bronson"}, 171, 1968));
		daoFilms.add(new DaoFilm("Django Unchained", "western", "Quentin Tarantino", new String[] {"Jamie Foxx", "Christoph Waltz", "Leonardo DiCaprio", "Kerry Washington"}, 165, 2012));
		daoFilms.add(new DaoFilm("Titanic", "drama", "James Cameron", new String[] {"Leonardo DiCaprio", "Kate Winslet", "Billy Zane", "Kathy Bates"}, 194, 1997));
		
		opinionsAsIntValues.put(DaoOpinion.VERY_BAD, 1);
		opinionsAsIntValues.put(DaoOpinion.RATHER_BAD, 2);
		opinionsAsIntValues.put(DaoOpinion.NEUTRAL, 3);
		opinionsAsIntValues.put(DaoOpinion.RATHER_GOOD, 4);
		opinionsAsIntValues.put(DaoOpinion.VERY_GOOD, 5);
		opinionsAsIntValues.put(DaoOpinion.NOT_FOUND, 0);
	}

	public Collection<DaoFilm> getAllFilms() {
		return daoFilms;
	}

	public Collection<DaoFilm> getAllFilmsOfGenre(String genreName) {
		List<DaoFilm> results = new ArrayList<DaoFilm>();
		
		for (DaoFilm daoFilm : daoFilms) {
			if (daoFilm.getGenre().equalsIgnoreCase(genreName)) {
				results.add(daoFilm);
			}
		}
		
		return results;
	}

	public Collection<DaoFilm> getAllFilmsOfDirector(String directorName) {
		List<DaoFilm> results = new ArrayList<DaoFilm>();
		
		for (DaoFilm daoFilm : daoFilms) {
			if (daoFilm.getDirector().equalsIgnoreCase(directorName)) {
				results.add(daoFilm);
			}
		}
		
		return results;
	}

	public Collection<DaoFilm> getAllFilmsOfActor(String actorName) {
		List<DaoFilm> results = new ArrayList<DaoFilm>();
		
		for (DaoFilm daoFilm : daoFilms) {
			for (String actor : daoFilm.getMainActors()) {
				if (actor.equalsIgnoreCase(actorName)) {
					results.add(daoFilm);
					break;
				}
			}
		}
		
		return results;
	}
	

	public Collection<DaoFilm> getAllFilmsBetweenLength(int minLengthInclusive, int maxLengthInclusive) {
		List<DaoFilm> results = new ArrayList<DaoFilm>();
		
		if (minLengthInclusive > maxLengthInclusive) {
			int tempMin = maxLengthInclusive;
			maxLengthInclusive = minLengthInclusive;
			minLengthInclusive = tempMin;
		}
		
		for (DaoFilm daoFilm : daoFilms) {
			int length = daoFilm.getMinutes();
			if (length >= minLengthInclusive && length <= maxLengthInclusive) {
				results.add(daoFilm);
			}
		}
		
		return results;
	}

	public DaoFilm getFilmData(String title) {
		for (DaoFilm daoFilm : daoFilms) {
			if (daoFilm.getTitle().equalsIgnoreCase(title)) {
				return daoFilm;
			}
		}
		return new DaoFilm(null, null, null, null, 0, 0);
	}

	public boolean addRating(String filmTitle, DaoOpinion daoOpinion) {
		DaoFilm daoFilm = getFilmData(filmTitle);
		if (daoFilm.getTitle() == null) {
			return false;
		} else {
			daoRatings.add(new DaoRating(daoFilm, daoOpinion));
			return true;
		}
	}
	
	public Collection<DaoRating> getRatingsOfFilm(String filmTitle) {
		List<DaoRating> ratingsOfFilm = new ArrayList<DaoRating>();
		for (DaoRating daoRating : daoRatings) {
			if (daoRating.getFilmTitle().equalsIgnoreCase(filmTitle)) {
				ratingsOfFilm.add(daoRating);
			}
		}
		return ratingsOfFilm;
	}
	
	public DaoOpinion getAverageRatingOfFilm(String filmTitle) {
		Collection<DaoRating> ratingsOfFilm = getRatingsOfFilm(filmTitle);
		if (ratingsOfFilm.size() == 0) {
			return DaoOpinion.NOT_FOUND;
		} else {
			int sum = 0;
			for (DaoRating daoRating : ratingsOfFilm) {
				sum += opinionsAsIntValues.get(daoRating.getOpinion());
			}
			double average = ((double) sum) / ((double) ratingsOfFilm.size());
			return getOpinionFromAverageRating(average);
		}
	}
	
	private DaoOpinion getOpinionFromAverageRating(double average) {
		int averageInt = (int) Math.round(average);
		for (DaoOpinion daoOpinion : opinionsAsIntValues.keySet()) {
			if (opinionsAsIntValues.get(daoOpinion) == averageInt) {
				return daoOpinion;
			}
		} return DaoOpinion.NOT_FOUND;
	}

}