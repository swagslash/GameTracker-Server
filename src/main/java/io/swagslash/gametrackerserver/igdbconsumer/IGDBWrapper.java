package io.swagslash.gametrackerserver.igdbconsumer;


import io.swagslash.gametrackerserver.igdbconsumer.model.IGDBCover;
import io.swagslash.gametrackerserver.igdbconsumer.model.IGDBGame;
import io.swagslash.gametrackerserver.igdbconsumer.model.IGDBGameMode;
import io.swagslash.gametrackerserver.igdbconsumer.model.IGDBGenre;
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
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

    private final Log logger = LogFactory.getLog(this.getClass());

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
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.set(apiHeader, apiKey);
        return new HttpEntity<>(query.toString(), headers);
    }

    /**
     * Get a list of games fitting the query
     */
    public List<IGDBGame> getGames(IGDBQuery query) {
        final String uri = API_URL + "/games";
        final HttpEntity<String> requestBody = generateRequestForQuery(query);

        ResponseEntity<IGDBGame[]> result;
        try {
            result = restTemplate.exchange(uri, HttpMethod.GET, requestBody, IGDBGame[].class);
        } catch (RestClientException e) {
            logger.error("Error when contacting the IGDB API for games", e);
            return null;
        }

        return Arrays.asList(Objects.requireNonNull(result.getBody()));
    }

    private List<IGDBCover> getCovers(IGDBQuery query) {
        final String uri = API_URL + "/covers";
        final HttpEntity<String> requestBody = generateRequestForQuery(query);

        ResponseEntity<IGDBCover[]> result;
        try {
            result = restTemplate.exchange(uri, HttpMethod.GET, requestBody, IGDBCover[].class);
        } catch (RestClientException e) {
            logger.error("Error when contacting the IGDB API for covers", e);
            return null;
        }

        return Arrays.asList(Objects.requireNonNull(result.getBody()));
    }


    public List<IGDBGame> searchGames(String searchTerm) {
        IGDBQuery query = new IGDBQuery();
        query.setSearch(searchTerm);
        return getGames(query);
    }

    public IGDBCover getCover(IGDBGame game) {
        return getCover(game.getCover());
    }

    public IGDBCover getCover(Integer coverId) {
        IGDBQuery query = new IGDBQuery();
        query.setWhere("id=" + coverId);
        List<IGDBCover> covers = getCovers(query);
        if (!covers.isEmpty()) return covers.get(0);
        else return null;
    }

    private List<IGDBGameMode> getGameModes(IGDBQuery query) {
        final String uri = API_URL + "/game_modes";
        final HttpEntity<String> requestBody = generateRequestForQuery(query);

        ResponseEntity<IGDBGameMode[]> result;
        try {
            result = restTemplate.exchange(uri, HttpMethod.GET, requestBody, IGDBGameMode[].class);
        } catch (RestClientException e) {
            logger.error("Error when contacting the IGDB API for gamemodes", e);
            return null;
        }

        return Arrays.asList(Objects.requireNonNull(result.getBody()));
    }

    public List<IGDBGameMode> getGameModes(IGDBGame game) {
        IGDBQuery query = new IGDBQuery();
        query.whereFieldEqualsAtLeastOne("id", game.getGame_modes());
        return getGameModes(query);
    }

    private List<IGDBGenre> getGenres(IGDBQuery query) {
        final String uri = API_URL + "/genres";
        final HttpEntity<String> requestBody = generateRequestForQuery(query);

        ResponseEntity<IGDBGenre[]> result;
        try {
            result = restTemplate.exchange(uri, HttpMethod.GET, requestBody, IGDBGenre[].class);
        } catch (RestClientException e) {
            logger.error("Error when contacting the IGDB API for genres", e);
            return null;
        }

        return Arrays.asList(Objects.requireNonNull(result.getBody()));
    }

    public List<IGDBGenre> getGenres(IGDBGame game) {
        IGDBQuery query = new IGDBQuery();
        query.whereFieldEqualsAtLeastOne("id", game.getGenres());
        return getGenres(query);
    }
}
