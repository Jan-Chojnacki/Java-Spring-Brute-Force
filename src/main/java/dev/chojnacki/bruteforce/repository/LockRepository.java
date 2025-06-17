package dev.chojnacki.bruteforce.repository;

import dev.chojnacki.bruteforce.model.Lock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;

public interface LockRepository extends JpaRepository<Lock, Long> {
    Lock findLockByIpAndTimestampAfter(String ip, Instant timestampAfter);
}
