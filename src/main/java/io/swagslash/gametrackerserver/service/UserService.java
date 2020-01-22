package io.swagslash.gametrackerserver.service;

import io.swagslash.gametrackerserver.model.User;

public interface UserService {

    User getCurrentUser();

    void save(User user);

    User getUserByName(String username);

}
