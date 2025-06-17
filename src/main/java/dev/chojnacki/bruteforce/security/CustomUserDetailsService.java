package dev.chojnacki.bruteforce.security;

import dev.chojnacki.bruteforce.model.Lock;
import dev.chojnacki.bruteforce.model.User;
import dev.chojnacki.bruteforce.service.AttemptService;
import dev.chojnacki.bruteforce.service.LockService;
import dev.chojnacki.bruteforce.service.UserService;
import jakarta.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Instant;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;
    private final LockService lockService;

    @Autowired
    public CustomUserDetailsService(UserService userService, LockService lockService, AttemptService attemptService) {
        this.userService = userService;
        this.lockService = lockService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Instant instant = Instant.now();

        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        ServletRequest servletRequest = servletRequestAttributes.getRequest();
        String ip = servletRequest.getRemoteHost();

        Lock lock = lockService.findLockByIpAndTimestampAfter(ip, instant);

        if (lock != null) throw new BadCredentialsException("Too many failed attempts");

        User user = userService.findUserByUsername(username);
        if (user == null) throw new UsernameNotFoundException("user not found");

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of()
        );
    }
}
