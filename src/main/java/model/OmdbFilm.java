package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OmdbFilm {
	
	private boolean Response = true;
	
	private String title;
	private String genre;
	private String director;
	private String actors;
	private String runtime;
	private int year;

	public OmdbFilm() {}

	public boolean isResponse() {
		return Response;
	}
	
	@JsonProperty("Response")
	public void setResponse(boolean response) {
		Response = response;
	}

	public String getTitle() {
		return title;
	}

	@JsonProperty("Title")
	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	@JsonProperty("Genre")
	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	@JsonProperty("Director")
	public void setDirector(String director) {
		this.director = director;
	}

	public String getActors() {
		return actors;
	}

	@JsonProperty("Actors")
	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getRuntime() {
		return runtime;
	}

	@JsonProperty("Runtime")
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public int getYear() {
		return year;
	}

	@JsonProperty("Year")
	public void setYear(int year) {
		this.year = year;
	}
	
}