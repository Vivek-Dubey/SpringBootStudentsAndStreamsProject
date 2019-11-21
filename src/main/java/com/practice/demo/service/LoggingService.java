package com.practice.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.demo.model.Logging;
import com.practice.demo.repository.LoggingRepository;

@Service
public class LoggingService {
	
	@Autowired
	private LoggingRepository loggingRepository;
	
	
	public void saveLog(String startTime, String url) {
	
		Logging logging =new Logging();
		
		logging.setStartTime(startTime);
		logging.setURL(url);
		logging.setEndTime(LocalDateTime.now().toString());
		
		loggingRepository.save(logging);
		
	}


	public List<Logging> getAllLogs() {
		return loggingRepository.findAll();
	}
	
	
	public List<Logging> getPagedLogs(int start,int size){
		List<Logging> log=loggingRepository.findAll();
		if((start+size) > log.size()) {
			return log.subList(start,log.size());
		}
		else if(start>log.size() || size==0) {
			return null;
		}
		
		return log.subList(start, start+size);
	}

}
