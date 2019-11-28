package io.swagslash.gametrackerserver.dto.agent;

public class AgentGame {

    private String gameName;
    private String gamePath;

    public AgentGame() {
    }

    public AgentGame(String gameName, String gamePath) {
        this.gameName = gameName;
        this.gamePath = gamePath;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGamePath() {
        return gamePath;
    }

    public void setGamePath(String gamePath) {
        this.gamePath = gamePath;
    }
}
