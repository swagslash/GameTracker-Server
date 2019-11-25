package io.swagslash.gametrackerserver.model;

import io.swagslash.gametrackerserver.model.ms.GameMS;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Game")
public class Game extends GameMS {

    @ManyToMany(mappedBy = "games")
    List<Tag> tags;

    @ManyToMany(mappedBy = "games")
    List<User> users;

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
}
