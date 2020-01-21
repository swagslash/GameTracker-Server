package io.swagslash.gametrackerserver.model;

import io.swagslash.gametrackerserver.model.ms.AgentTokenMS;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
