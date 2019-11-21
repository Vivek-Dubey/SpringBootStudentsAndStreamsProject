package com.practice.demo.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.demo.model.Stream;
import com.practice.demo.service.LoggingService;
import com.practice.demo.service.StreamService;


@RestController
@RequestMapping("/stream")
public class StreamController {
	
	Logger logger = LoggerFactory.getLogger(StreamController.class);

	@Autowired
	private StreamService streamService;
	
	@Autowired
	private LoggingService loggingService;
	
	String startTime;
	

	@GetMapping("/getAll")
	public List<Stream> getAllStreams(HttpServletRequest request){
		logger.trace("All stream returned");
		
		startTime = LocalDateTime.now().toString();
		
		List<Stream> result =  streamService.getAllStreams();
		
		loggingService.saveLog(startTime,request.getRequestURI());
		
		return result;
	}
	
	@GetMapping("/get/{id}")
	public Optional<Stream> getStream(@PathVariable int id,HttpServletRequest request)
	{	
		logger.trace("Stream retuned with id: "+id);
		
		startTime = LocalDateTime.now().toString();
				
		Optional<Stream> result=streamService.getStream(id);
		
		loggingService.saveLog(startTime,request.getRequestURI());
		
		return result;
	}
	
	@PostMapping(value = "/add")
	public void addStream(@RequestBody Stream stream,HttpServletRequest request) {
		
		logger.trace("New stream aaded");
		
		startTime = LocalDateTime.now().toString();
		
		streamService.addStream(stream);

		loggingService.saveLog(startTime,request.getRequestURI());
	}
	
	@PutMapping(value = "/update/{id}")
	public void updateStream(@RequestBody Stream stream, @PathVariable int id,HttpServletRequest request) {
		
		logger.trace("Stream updated with id: "+id);
		
		startTime = LocalDateTime.now().toString();
		
		streamService.updateStream(stream,id);
		
		loggingService.saveLog(startTime,request.getRequestURI());
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public void deleteStream(@PathVariable int id,HttpServletRequest request)
	{
		logger.trace("Stream deleted with id: "+id);
		
		startTime = LocalDateTime.now().toString();
		
		streamService.deleteStream(id);
		
		loggingService.saveLog(startTime,request.getRequestURI());
	}
}
