package dev.chojnacki.bruteforce.repository;

import dev.chojnacki.bruteforce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
