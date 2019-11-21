package com.practice.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.demo.model.Stream;

public interface StreamRepository extends JpaRepository<Stream, Integer> {

	public Stream findByStreamId(int id);

}
