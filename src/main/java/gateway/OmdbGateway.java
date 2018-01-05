package gateway;

import modelForControllerIn.OmdbFilm;

import org.springframework.integration.annotation.Payload;

public interface OmdbGateway {

	OmdbFilm getOmdbFilmData(@Payload String title);
	
}