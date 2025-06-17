package dev.chojnacki.bruteforce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
public class Attempt {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    String ip;
    @Column
    Instant timestamp;
}
