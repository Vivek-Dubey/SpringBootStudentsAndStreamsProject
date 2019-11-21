package com.practice.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.demo.model.Stream;
import com.practice.demo.repository.StreamRepository;

@Service
public class StreamService {
	
	@Autowired
	private StreamRepository streamRepository;
	

	public List<Stream> getAllStreams() {
		List<Stream> stream = new ArrayList<>();
		streamRepository.findAll().forEach(stream::add);
		return stream;
	}

	public Optional<Stream> getStream(int id) {
		return streamRepository.findById(id);
	}

	public void addStream(Stream stream) {
		streamRepository.save(stream);
	}

	public void updateStream(Stream stream,int id) {
		Stream st=streamRepository.findByStreamId(id);
		if(st!=null) {
		st.setStream(stream.getStream());
		streamRepository.save(st);
		}
		else {
			System.out.println("Stream with Id :"+ id +" is not available");
		}
	}

	public void deleteStream(int id) {
		streamRepository.deleteById(id);
	}

}
