package com.practice.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.demo.model.Logging;
import com.practice.demo.service.LoggingService;

@RestController
@RequestMapping("/logs")
public class LoggingController {
	
	@Autowired
	private LoggingService loggingService;
	
	@GetMapping(value = "/getAll")
	public List<Logging> getAllLogs(@RequestParam(name = "start",required = false,defaultValue = "0") int start,
									@RequestParam(name = "size", required = false,defaultValue = "0") int size)
	{
		if(start > 0 && size > 0) {
			return loggingService.getPagedLogs(start-1, size);
		}
		return loggingService.getAllLogs();
	}
}
