package io.swagslash.gametrackerserver.igdbconsumer;


import io.swagslash.gametrackerserver.igdbconsumer.model.Cover;
import io.swagslash.gametrackerserver.igdbconsumer.model.Game;
import io.swagslash.gametrackerserver.igdbconsumer.model.GameMode;
import io.swagslash.gametrackerserver.igdbconsumer.model.Genre;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Wraps parts of the IGBG v3 API
 * Documentation on https://api-docs.igdb.com/
 *
 * @Author: Christoph Wedenig (christoph@wedenig.org)
 */
public class IGDBWrapper implements IGDBApi {
    private final String API_URL = "https://api-v3.igdb.com";
    private String apiHeader = "user-key";
    private String apiKey;

    private RestTemplate restTemplate = new RestTemplate();

    protected final Log logger = LogFactory.getLog(this.getClass());

    public IGDBWrapper(String apiKey) {
        this.apiKey = apiKey;
        // this makes the restTemplate also send the body on GET requests
        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestWithBodyFactory());
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static final class HttpComponentsClientHttpRequestWithBodyFactory extends HttpComponentsClientHttpRequestFactory {
        @Override
        protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
            if (httpMethod == HttpMethod.GET) {
                return new HttpGetRequestWithEntity(uri);
            }
            return super.createHttpUriRequest(httpMethod, uri);
        }
    }

    private static final class HttpGetRequestWithEntity extends HttpEntityEnclosingRequestBase {
        public HttpGetRequestWithEntity(final URI uri) {
            super.setURI(uri);
        }

        @Override
        public String getMethod() {
            return HttpMethod.GET.name();
        }
    }

    private HttpEntity<String> generateRequestForQuery(IGDBQuery query) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.set(apiHeader, apiKey);
        HttpEntity<String> entity = new HttpEntity<String>(query.toString(), headers);
        return entity;
    }

    /**
     * Get a list of games fitting the query
     */
    public List<Game> getGames(IGDBQuery query) {
        final String uri = API_URL + "/games";
        final HttpEntity<String> requestBody = generateRequestForQuery(query);

        ResponseEntity<Game[]> result = null;
        try {
            result = restTemplate.exchange(uri, HttpMethod.GET, requestBody, Game[].class);
        } catch (RestClientException e) {
            logger.error("Error when contacting the IGDB API for games", e);
            return null;
        }

        return Arrays.asList(Objects.requireNonNull(result.getBody()));
    }

    private List<Cover> getCovers(IGDBQuery query) {
        final String uri = API_URL + "/covers";
        final HttpEntity<String> requestBody = generateRequestForQuery(query);

        ResponseEntity<Cover[]> result = null;
        try {
            result = restTemplate.exchange(uri, HttpMethod.GET, requestBody, Cover[].class);
        } catch (RestClientException e) {
            logger.error("Error when contacting the IGDB API for covers", e);
            return null;
        }

        return Arrays.asList(Objects.requireNonNull(result.getBody()));
    }


    public List<Game> searchGames(String searchTerm) {
        IGDBQuery query = new IGDBQuery();
        query.setSearch(searchTerm);
        return getGames(query);
    }

    public Cover getCover(Game game) {
        return getCover(game.getCover());
    }

    public Cover getCover(Integer coverId) {
        IGDBQuery query = new IGDBQuery();
        query.setWhere("id=" + coverId);
        return getCovers(query).get(0);
    }

    private List<GameMode> getGameModes(IGDBQuery query) {
        final String uri = API_URL + "/game_modes";
        final HttpEntity<String> requestBody = generateRequestForQuery(query);

        ResponseEntity<GameMode[]> result = null;
        try {
            result = restTemplate.exchange(uri, HttpMethod.GET, requestBody, GameMode[].class);
        } catch (RestClientException e) {
            logger.error("Error when contacting the IGDB API for gamemodes", e);
            return null;
        }

        return Arrays.asList(Objects.requireNonNull(result.getBody()));
    }

    public List<GameMode> getGameModes(Game game) {
        IGDBQuery query = new IGDBQuery();

        String whereString = Arrays.stream(game.getGame_modes())
                .mapToObj(i -> "id=" + i)
                .collect(Collectors.joining("|"));

        query.setWhere(whereString);
        return getGameModes(query);
    }

    private List<Genre> getGenres(IGDBQuery query) {
        final String uri = API_URL + "/genres";
        final HttpEntity<String> requestBody = generateRequestForQuery(query);

        ResponseEntity<Genre[]> result = null;
        try {
            result = restTemplate.exchange(uri, HttpMethod.GET, requestBody, Genre[].class);
        } catch (RestClientException e) {
            logger.error("Error when contacting the IGDB API for genres", e);
            return null;
        }

        return Arrays.asList(Objects.requireNonNull(result.getBody()));
    }

    public List<Genre> getGenres(Game game) {
        IGDBQuery query = new IGDBQuery();

        String whereString = Arrays.stream(game.getGenres())
                .mapToObj(i -> "id=" + i)
                .collect(Collectors.joining("|"));

        query.setWhere(whereString);
        return getGenres(query);
    }
}
