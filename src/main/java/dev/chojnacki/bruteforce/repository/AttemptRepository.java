package dev.chojnacki.bruteforce.repository;

import dev.chojnacki.bruteforce.model.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {
    List<Attempt> findAttemptsByIpAndTimestampAfter(String ip, Instant timestampAfter);
}
