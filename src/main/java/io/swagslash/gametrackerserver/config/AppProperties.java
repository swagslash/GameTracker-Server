package io.swagslash.gametrackerserver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for accessing application propertiers speficied in Application.yaml
 */
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private final Auth auth = new Auth();
    private final OAuth2 oauth2 = new OAuth2();
    private final IGDB igdb = new IGDB();

    /**
     * Maps Auth section of application.yaml
     */
    public static class Auth {

        private String tokenSecret;
        private long tokenExpirationMsec;

        public String getTokenSecret() {
            return tokenSecret;
        }

        public void setTokenSecret(String tokenSecret) {
            this.tokenSecret = tokenSecret;
        }

        public long getTokenExpirationMsec() {
            return tokenExpirationMsec;
        }

        public void setTokenExpirationMsec(long tokenExpirationMsec) {
            this.tokenExpirationMsec = tokenExpirationMsec;
        }
    }

    /**
     * Maps all third party information for oauth2 authentication from application.yaml
     */
    public static final class OAuth2 {
        private List<String> authorizedRedirectUris = new ArrayList<>();

        public List<String> getAuthorizedRedirectUris() {
            return authorizedRedirectUris;
        }

        public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
            this.authorizedRedirectUris = authorizedRedirectUris;
            return this;
        }
    }

    /**
     * Maps IGDB section of application.yaml
     */
    public static final class IGDB {
        private String key;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }

    public Auth getAuth() {
        return auth;
    }

    public OAuth2 getOauth2() {
        return oauth2;
    }

    public IGDB getIgdb() {
        return igdb;
    }
}
