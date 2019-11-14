package io.swagslash.gametrackerserver.model;

import io.swagslash.gametrackerserver.model.ms.UserMS;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class User extends UserMS {

}
