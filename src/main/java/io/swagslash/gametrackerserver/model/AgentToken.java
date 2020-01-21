package io.swagslash.gametrackerserver.model;

import io.swagslash.gametrackerserver.model.ms.AgentTokenMS;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "AgentToken")
public class AgentToken extends AgentTokenMS {

    @OneToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
