package gateway;

import model.OmdbFilm;

import org.springframework.integration.annotation.Payload;

public interface OmdbGateway {

	OmdbFilm getOmdbFilmData(@Payload String title);
	
}