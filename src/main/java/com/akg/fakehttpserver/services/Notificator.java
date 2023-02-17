package com.akg.fakehttpserver.services;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.akg.fakehttpserver.config.Config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Notificator {
	
	private int whenToNotifyCounter;
	private final Lock lock = new ReentrantLock();
	
	@Autowired
	private Config config;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PostConstruct
	public void init() {
		whenToNotifyCounter = config.getNumberOfExecutions();
		log.info("Notificator was setup with a counter: {}", whenToNotifyCounter);
	}

	public boolean countdownAndNotifyIfReady() {

		lock.lock();
		try { 
			whenToNotifyCounter--;
			log.info("Notificator is not ready {}", whenToNotifyCounter);

			if( whenToNotifyCounter == 0 ) {
				restTemplate.getForObject(config.getNotificatorUrl(), String.class);
				log.info("The notification has triggered. Countdown is reset.");
				// reset the counter
				whenToNotifyCounter = config.getNumberOfExecutions();
				return true;
			}
		} finally {
			lock.unlock();
		}

		return false;
	}
}
