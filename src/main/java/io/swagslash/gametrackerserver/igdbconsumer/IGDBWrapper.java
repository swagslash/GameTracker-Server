package io.swagslash.gametrackerserver.igdbconsumer;


import io.swagslash.gametrackerserver.igdbconsumer.model.Game;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

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

    public IGDBWrapper(String apiKey) {
        this.apiKey = apiKey;
        // this makes the restTemplate also send the body on GET requests
        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestWithBodyFactory());
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

    /**
     * Get a list of games fitting the query
     *
     * @param query language to use: https://apicalypse.io
     * @return
     */
    private List<Game> getGames(IGDBQuery query) {
        final String uri = API_URL + "/games";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.set(apiHeader, apiKey);
        HttpEntity<String> entity = new HttpEntity<String>(query.toString(), headers);

        ResponseEntity<Game[]> result = null;
        try {
            result = restTemplate.exchange(uri, HttpMethod.GET, entity, Game[].class);
        } catch (RestClientException e) {
            e.printStackTrace();
        }

        return Arrays.asList(result.getBody());
    }

    public static void main(String[] args) {
        IGDBWrapper consumer = new IGDBWrapper("26a20509aca971d614544cdec65f28e0");
        IGDBQuery query = new IGDBQuery();
        query.addField("name");
        query.addField("cover");
        query.addField("game_modes");
        consumer.getGames(query);
        List<Game> csgo = consumer.searchGames("cs1.6");
        System.out.println("done");
    }

    public List<Game> searchGames(String searchTerm) {
        IGDBQuery query = new IGDBQuery();
        query.setSearch(searchTerm);
        return getGames(query);
    }
}
