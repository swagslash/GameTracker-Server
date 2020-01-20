package io.swagslash.gametrackerserver.model;

import io.swagslash.gametrackerserver.enums.TagTypeEnum;
import io.swagslash.gametrackerserver.model.ms.GameMS;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Game")
public class Game extends GameMS {

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "game_tag",
            joinColumns = @JoinColumn(name = "tag_Id"),
            inverseJoinColumns = @JoinColumn(name = "game_Id")
    )
    private List<Tag> tags;

    @ManyToMany(mappedBy = "games")
    private List<User> users;

    public Game() {
        tags = new ArrayList<>();
        users = new ArrayList<>();
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Tag> getGenres() {
        return tags.stream()
                .filter(tag -> tag.getType().equals(TagTypeEnum.GENRE))
                .collect(Collectors.toList());
    }

    public List<Tag> getGameModes() {
        return tags.stream()
                .filter(tag -> tag.getType().equals(TagTypeEnum.GENRE))
                .collect(Collectors.toList());
    }

    public boolean isOwnedByUser(String username) {
        return users.stream().anyMatch(str -> str.equals(username));
    }
}
