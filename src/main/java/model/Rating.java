package model;

public class Rating {

	private Film film;
	private Opinion opinion;
	
	public Rating(Film film, Opinion opinion) {
		this.film = film;
		this.opinion = opinion;
	}
	
	public String getFilmTitle() {
		return film.getTitle();
	}

	public Opinion getOpinion() {
		return opinion;
	}

	public void setOpinion(Opinion opinion) {
		this.opinion = opinion;
	}
	
}