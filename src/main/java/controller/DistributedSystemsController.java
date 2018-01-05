package controller;

import gateway.OmdbGateway;

import java.util.Collection;

import modelForControllerIn.OmdbFilm;
import modelForControllerOut.Film;
import modelForControllerOut.Opinion;
import modelForControllerOut.Rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import service.ServiceInterface;

@Controller
public class DistributedSystemsController {

	@Autowired
	private ServiceInterface service;
	
	private OmdbGateway gateway;
	
	public DistributedSystemsController(ServiceInterface service) {
		this.service = service;
		
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("si-config.xml");
		gateway = context.getBean("omdbGateway", OmdbGateway.class);
	}
	
	@RequestMapping(value = "/getAllFilms")
    @ResponseBody
    public Collection<Film> getAllFilms() {
        return service.getAllFilms();
    }
	
	@RequestMapping(value = "/getAllFilmsOfGenre")
    @ResponseBody
    public Collection<Film> getAllFilmsOfGenre(@RequestParam("genre") String genre) {
        return service.getAllFilmsOfGenre(genre);
    }
	
	@RequestMapping(value = "/getAllFilmsOfDirector")
    @ResponseBody
    public Collection<Film> getAllFilmsOfDirector(@RequestParam("director") String director) {
        return service.getAllFilmsOfDirector(director);
    }
	
	@RequestMapping(value = "/getAllFilmsOfActor")
    @ResponseBody
    public Collection<Film> getAllFilmsOfActor(@RequestParam("actor") String actor) {
        return service.getAllFilmsOfActor(actor);
    }
	
	@RequestMapping(value = "/getAllFilmsBetweenLength")
    @ResponseBody
    public Collection<Film> getAllFilmsBetweenLength(@RequestHeader("min") int min, @RequestHeader("max") int max) {
        return service.getAllFilmsBetweenLength(min, max);
    }
	
	@RequestMapping(value = "/getFilmData")
    @ResponseBody
    public Film getFilmData(@RequestParam("title") String filmTitle) {
        return service.getFilmData(filmTitle);
    }
	
	@RequestMapping(value = "/addRating")
    @ResponseBody
    public boolean addRating(@RequestParam("title") String filmTitle, @RequestHeader("opinion") Opinion opinion) {
		if (opinion == Opinion.NOT_FOUND) {
			return false;
		}
        return service.addRating(filmTitle, opinion);
    }
	
	@RequestMapping(value = "/getRatingsOfFilm")
    @ResponseBody
    public Collection<Rating> getRatingsOfFilm(@RequestParam("title") String filmTitle) {
        return service.getRatingsOfFilm(filmTitle);
    }
	
	@RequestMapping(value = "/getAverageRatingOfFilm")
    @ResponseBody
    public Opinion getAverageRatingOfFilm(@RequestParam("title") String filmTitle) {
        return service.getAverageRatingOfFilm(filmTitle);
    }
	
	@RequestMapping(value = "/getOmdbFilmData")
    @ResponseBody
    public Film getOmdbFilmData(@RequestParam("title") String filmTitle) {
		try {
	        OmdbFilm omdbFilm = gateway.getOmdbFilmData(filmTitle);
	        return service.convertOmdbFilm(omdbFilm);
		} catch (Exception e) {
			return service.emptyFilmData();
		}
    }
	
}