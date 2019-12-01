package io.swagslash.gametrackerserver.model;

import io.swagslash.gametrackerserver.model.ms.AgentTokenMS;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "AgentToken")
public class AgentToken extends AgentTokenMS {
}
