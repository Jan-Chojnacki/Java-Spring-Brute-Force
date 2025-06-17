package dev.chojnacki.bruteforce.service;

import dev.chojnacki.bruteforce.model.Lock;

import java.time.Instant;

public interface LockService {
    Lock findLockByIpAndTimestampAfter(String ip, Instant timestampAfter);

    Lock addLock(String ip, Instant timestamp);
}
