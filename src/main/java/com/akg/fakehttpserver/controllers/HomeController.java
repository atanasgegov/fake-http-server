package com.akg.fakehttpserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.akg.fakehttpserver.services.Worker;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@Autowired
	private Worker sleepService;
	
	@RequestMapping("/imitateWork")
	public ResponseEntity<String> processWorkForMs(@RequestParam(name = "timeInMs", required = false, defaultValue = "100") long timeInMs) {
		try {
			sleepService.sleep(timeInMs);
		} catch (InterruptedException e) {
			
			log.error("Something wrong happened!", e);
		    Thread.currentThread().interrupt();
			new ResponseEntity<>("Something wrong happened!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(String.format("Service did the work for %s ms.", timeInMs), HttpStatus.OK);
	}
}