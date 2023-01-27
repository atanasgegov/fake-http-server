package com.akg.fakehttpserver.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@RequestMapping("/imitateWorkForMs")
	public ResponseEntity<String> processWorkForMs(@RequestParam(name = "timeInMs", required = false, defaultValue = "100") long timeInMs) {
		try {
			Thread.sleep(timeInMs);
		} catch (InterruptedException e) {
			
			log.error("Something wrong happened!", e);
		    Thread.currentThread().interrupt();
			new ResponseEntity<>("Something wrong happened!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(String.format("Service did the work for %s ms.", timeInMs), HttpStatus.OK);
	}
}