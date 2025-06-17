package dev.chojnacki.bruteforce.service;

import dev.chojnacki.bruteforce.model.Attempt;

import java.time.Instant;
import java.util.List;

public interface AttemptService {
    List<Attempt> findAttemptsByIpAndTimestampAfter(String ip, Instant timestampAfter);

    Attempt addAttempt(String ip, Instant timestamp);
}
