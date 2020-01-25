package com.slusarz.pokerafterdark.spring.configuration.application;

import com.slusarz.pokerafterdark.spring.authentication.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class AuthenticationScheduler {

    @Autowired
    private AuthenticationService authenticationService;

    @Scheduled(cron = "0 7 * * * ?")
    public void schedule() {
        authenticationService.removeExpiredTokens();
    }

}
