package com.akg.fakehttpserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Worker {

	@Autowired
	private Notificator notificator;
	
	public boolean sleep(long timeInMs) throws InterruptedException {
		Thread.sleep(timeInMs);
		
		notificator.countdownAndNotifyIfReady();
		
		return true;
	}
}