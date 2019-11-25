package io.swagslash.gametrackerserver.igdbconsumer;


import io.swagslash.gametrackerserver.igdbconsumer.model.IGDBCover;
import io.swagslash.gametrackerserver.igdbconsumer.model.IGDBGame;
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

/**
 * Wraps parts of the IGBG v3 API
 * Documentation on https://api-docs.igdb.com/
 *
 * @Author: Christoph Wedenig (christoph@wedenig.org)
 */
public class IGDBWrapper {
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
    private List<IGDBGame> getGames(IGDBQuery query) {
        final String uri = API_URL + "/games";
        final HttpEntity<String> requestBody = generateRequestForQuery(query);

        ResponseEntity<IGDBGame[]> result = null;
        try {
            result = restTemplate.exchange(uri, HttpMethod.GET, requestBody, IGDBGame[].class);
        } catch (RestClientException e) {
            logger.error("Error when contacting the IGDB API for games", e);
            return null;
        }

        return Arrays.asList(Objects.requireNonNull(result.getBody()));
    }

    private List<IGDBCover> getCover(IGDBQuery query) {
        final String uri = API_URL + "/covers";
        final HttpEntity<String> requestBody = generateRequestForQuery(query);

        ResponseEntity<IGDBCover[]> result = null;
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

    public List<IGDBCover> getCover(IGDBGame game) {
        return getCover(game.getCover());
    }

    public List<IGDBCover> getCover(Integer coverId) {
        IGDBQuery query = new IGDBQuery();
        query.setWhere("id=" + coverId);
        return getCover(query);
    }
}
