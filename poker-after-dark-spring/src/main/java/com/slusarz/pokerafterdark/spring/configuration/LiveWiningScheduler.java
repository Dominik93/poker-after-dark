package com.slusarz.pokerafterdark.spring.configuration;

import com.slusarz.pokerafterdark.aplication.livewinnings.LiveWinningsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class LiveWiningScheduler {

    @Autowired
    private LiveWinningsRepository liveWinningsJpaRepository;

    @Transactional
    @Scheduled(cron = "0 7 * * * ?")
    public void schedule() {
        liveWinningsJpaRepository.synchronize();
    }

}
