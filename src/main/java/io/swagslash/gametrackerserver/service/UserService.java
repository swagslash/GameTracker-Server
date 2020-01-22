package io.swagslash.gametrackerserver.service;

import io.swagslash.gametrackerserver.model.User;

import java.util.List;

public interface UserService {

    User getCurrentUser();

    void save(User user);

    User getUserByName(String username);

    List<String> findUsernamesByTerm(String term);

}
