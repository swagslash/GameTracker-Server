package io.swagslash.gametrackerserver.igdbconsumer.model;

public class IGDBCover {
    private boolean alpha_channel;
    private boolean animated;
    private int game; // The game this cover is associated with
    private int height; // The height of the image in pixels
    private String image_id; // The ID of the image used to construct an IGDB image link
    private String url;
    private int width;

    public boolean isAlpha_channel() {
        return alpha_channel;
    }

    public void setAlpha_channel(boolean alpha_channel) {
        this.alpha_channel = alpha_channel;
    }

    public boolean isAnimated() {
        return animated;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
    }

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "IGDBCover{" +
                "alpha_channel=" + alpha_channel +
                ", animated=" + animated +
                ", game=" + game +
                ", height=" + height +
                ", image_id='" + image_id + '\'' +
                ", url='" + url + '\'' +
                ", width=" + width +
                '}';
    }
}
