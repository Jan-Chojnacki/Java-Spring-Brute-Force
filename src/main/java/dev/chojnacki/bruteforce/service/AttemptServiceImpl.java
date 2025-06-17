package dev.chojnacki.bruteforce.service;

import dev.chojnacki.bruteforce.model.Attempt;
import dev.chojnacki.bruteforce.repository.AttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class AttemptServiceImpl implements AttemptService {
    private final AttemptRepository attemptRepository;

    @Autowired
    public AttemptServiceImpl(AttemptRepository attemptRepository) {
        this.attemptRepository = attemptRepository;
    }

    @Override
    public List<Attempt> findAttemptsByIpAndTimestampAfter(String ip, Instant timestampAfter) {
        return attemptRepository.findAttemptsByIpAndTimestampAfter(ip, timestampAfter);
    }

    @Override
    public Attempt addAttempt(String ip, Instant timestamp) {
        Attempt attempt = new Attempt();
        attempt.setIp(ip);
        attempt.setTimestamp(timestamp);

        return attemptRepository.save(attempt);
    }
}
