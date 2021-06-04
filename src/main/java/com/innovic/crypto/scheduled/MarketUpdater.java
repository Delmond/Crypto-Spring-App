package com.innovic.crypto.scheduled;

import java.time.LocalTime;

import com.innovic.crypto.service.IMarketUpdaterService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MarketUpdater {
    @Autowired
    private IMarketUpdaterService cncService;

    private Logger logger = LoggerFactory.getLogger(MarketUpdater.class);

    @Scheduled(initialDelay=0, fixedRate=60*60*1000)
    public void updateEveryHour(){
        cncService.update();
        logger.info("Updated database at: " + LocalTime.now().toString());
    }

}
