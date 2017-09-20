package com.hero.fun.schedules;

import java.util.Date;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class ScheduledTasks {

    //private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    //private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	//minute (0-59), hour (0-23, 0 = midnight), day (1-31), month (1-12), weekday (0-6, 0 = Sunday).
    @Scheduled(cron="0 0 * * * ?")
    public void reportCurrentTime() {
    	log.debug("The time is now {}"+ new Date());
    }
}