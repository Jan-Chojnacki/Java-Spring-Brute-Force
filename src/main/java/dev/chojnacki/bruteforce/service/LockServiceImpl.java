package dev.chojnacki.bruteforce.service;

import dev.chojnacki.bruteforce.model.Lock;
import dev.chojnacki.bruteforce.repository.LockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class LockServiceImpl implements LockService {
    private final LockRepository lockRepository;

    @Autowired
    public LockServiceImpl(LockRepository lockRepository) {
        this.lockRepository = lockRepository;

    }

    @Override
    public Lock findLockByIpAndTimestampAfter(String ip, Instant timestampAfter) {
        return lockRepository.findLockByIpAndTimestampAfter(ip, timestampAfter);
    }

    @Override
    public Lock addLock(String ip, Instant timestamp) {
        Lock lock = new Lock();
        lock.setIp(ip);
        lock.setTimestamp(timestamp);

        return lockRepository.save(lock);
    }
}
