package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Film;
import model.Opinion;
import model.Rating;

public class DaoImpl implements DaoInterface {
	
	private Collection<Film> films = new ArrayList<Film>();
	private Collection<Rating> ratings = new ArrayList<Rating>();
	
	private Map<Opinion, Integer> opinionsAsIntValues = new HashMap<Opinion, Integer>();
	
	public DaoImpl() {
		films.add(new Film("Once Upon a Time in the West", "western", "Sergio Leone", new String[] {"Henry Fonda", "Claudia Cardinale"}, 171, 1968));
		films.add(new Film("Django Unchained", "western", "Quentin Tarantino", new String[] {"Jamie Foxx", "Christoph Waltz", "Leonardo DiCaprio"}, 165, 2012));
		films.add(new Film("Titanic", "drama", "James Cameron", new String[] {"Leonardo DiCaprio", "Kate Winslet"}, 194, 1997));
		
		opinionsAsIntValues.put(Opinion.VERY_BAD, 1);
		opinionsAsIntValues.put(Opinion.RATHER_BAD, 2);
		opinionsAsIntValues.put(Opinion.NEUTRAL, 3);
		opinionsAsIntValues.put(Opinion.RATHER_GOOD, 4);
		opinionsAsIntValues.put(Opinion.VERY_GOOD, 5);
		opinionsAsIntValues.put(Opinion.NOT_FOUND, 0);
	}

	public Collection<Film> getAllFilms() {
		return films;
	}

	public Collection<Film> getAllFilmsOfGenre(String genreName) {
		List<Film> results = new ArrayList<Film>();
		
		for (Film film : films) {
			if (film.getGenre().equalsIgnoreCase(genreName)) {
				results.add(film);
			}
		}
		
		return results;
	}

	public Collection<Film> getAllFilmsOfDirector(String directorName) {
		List<Film> results = new ArrayList<Film>();
		
		for (Film film : films) {
			if (film.getDirector().equalsIgnoreCase(directorName)) {
				results.add(film);
			}
		}
		
		return results;
	}

	public Collection<Film> getAllFilmsOfActor(String actorName) {
		List<Film> results = new ArrayList<Film>();
		
		for (Film film : films) {
			for (String actor : film.getMainActors()) {
				if (actor.equalsIgnoreCase(actorName)) {
					results.add(film);
					break;
				}
			}
		}
		
		return results;
	}
	

	public Collection<Film> getAllFilmsBetweenLength(int minLengthInclusive, int maxLengthInclusive) {
		List<Film> results = new ArrayList<Film>();
		
		for (Film film : films) {
			int length = film.getMinutes();
			if (length >= minLengthInclusive && length <= maxLengthInclusive) {
				results.add(film);
			}
		}
		
		return results;
	}

	public Film getFilmData(String title) {
		for (Film film : films) {
			if (film.getTitle().equalsIgnoreCase(title)) {
				return film;
			}
		}
		return new Film(null, null, null, null, 0, 0);
	}

	public boolean addRating(String filmTitle, Opinion opinion) {
		Film film = getFilmData(filmTitle);
		if (film.getTitle() == null) {
			return false;
		} else {
			ratings.add(new Rating(film, opinion));
			return true;
		}
	}
	
	public Collection<Rating> getRatingsOfFilm(String filmTitle) {
		List<Rating> ratingsOfFilm = new ArrayList<Rating>();
		for (Rating rating : ratings) {
			if (rating.getFilmTitle().equalsIgnoreCase(filmTitle)) {
				ratingsOfFilm.add(rating);
			}
		}
		return ratingsOfFilm;
	}
	
	public Opinion getAverageRatingOfFilm(String filmTitle) {
		Collection<Rating> ratingsOfFilm = getRatingsOfFilm(filmTitle);
		if (ratingsOfFilm.size() == 0) {
			return Opinion.NOT_FOUND;
		} else {
			int sum = 0;
			for (Rating rating : ratingsOfFilm) {
				sum += opinionsAsIntValues.get(rating.getOpinion());
			}
			double average = ((double) sum) / ((double) ratingsOfFilm.size());
			return getOpinionFromAverageRating(average);
		}
	}
	
	private Opinion getOpinionFromAverageRating(double average) {
		int averageInt = (int) Math.round(average);
		for (Opinion opinion : opinionsAsIntValues.keySet()) {
			if (opinionsAsIntValues.get(opinion) == averageInt) {
				return opinion;
			}
		} return Opinion.NOT_FOUND;
	}

}