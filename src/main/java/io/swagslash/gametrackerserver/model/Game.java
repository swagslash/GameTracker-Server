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

}
