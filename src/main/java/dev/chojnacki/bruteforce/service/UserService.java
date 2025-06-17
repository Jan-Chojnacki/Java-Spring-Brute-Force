package dev.chojnacki.bruteforce.service;

import dev.chojnacki.bruteforce.model.User;

public interface UserService {
    User findUserByUsername(String username);
}
