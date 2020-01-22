package io.swagslash.gametrackerserver.service.impl;

import io.swagslash.gametrackerserver.model.User;
import io.swagslash.gametrackerserver.repository.UserRepository;
import io.swagslash.gametrackerserver.security.UserPrincipal;
import io.swagslash.gametrackerserver.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getCurrentUser() {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userRepository.findByEmail(principal.getEmail()).get();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<String> findUsernamesByTerm(String term) {
        return userRepository.findAll()
                .stream()
                .map(user -> user.getUsername())
                .filter(username -> username.contains(term))
                .collect(Collectors.toList());
    }
}
