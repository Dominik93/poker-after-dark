package com.slusarz.pokerafterdark.spring.configuration.application;

import com.slusarz.pokerafterdark.application.livewinnings.PlayerProjectionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class LiveWiningScheduler {

    @Autowired
    private PlayerProjectionRepository playerProjectionRepository;

    @Transactional
    @Scheduled(cron = "0 7 * * * ?")
    public void schedule() {
        playerProjectionRepository.synchronize();
    }

}
