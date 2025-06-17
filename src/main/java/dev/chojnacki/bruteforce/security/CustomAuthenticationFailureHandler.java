package dev.chojnacki.bruteforce.security;

import dev.chojnacki.bruteforce.model.Attempt;
import dev.chojnacki.bruteforce.service.AttemptService;
import dev.chojnacki.bruteforce.service.LockService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private final LockService lockService;
    private final AttemptService attemptService;

    @Autowired
    public CustomAuthenticationFailureHandler(LockService lockService, AttemptService attemptService) {
        this.lockService = lockService;
        this.attemptService = attemptService;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String ip = request.getRemoteHost();

        List<Attempt> attempts = attemptService.findAttemptsByIpAndTimestampAfter(ip, Instant.now().minus(Duration.ofMinutes(15)));

        if (attempts.size() >= 5) {
            lockService.addLock(ip, Instant.now().plus(Duration.ofMinutes(15)));
            return;
        }

        attemptService.addAttempt(ip, Instant.now());

        super.onAuthenticationFailure(request, response, exception);
    }
}
