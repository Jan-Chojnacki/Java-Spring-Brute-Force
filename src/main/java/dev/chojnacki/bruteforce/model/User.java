package dev.chojnacki.bruteforce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
}
