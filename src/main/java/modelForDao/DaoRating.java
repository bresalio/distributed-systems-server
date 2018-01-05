package modelForDao;

public class DaoRating {

	private DaoFilm film;
	private DaoOpinion opinion;
	
	public DaoRating(DaoFilm daoFilm, DaoOpinion daoOpinion) {
		this.film = daoFilm;
		this.opinion = daoOpinion;
	}
	
	public String getFilmTitle() {
		return film.getTitle();
	}
	
	public DaoFilm getFilm() {
		return film;
	}

	public DaoOpinion getOpinion() {
		return opinion;
	}

	public void setOpinion(DaoOpinion daoOpinion) {
		this.opinion = daoOpinion;
	}
	
}