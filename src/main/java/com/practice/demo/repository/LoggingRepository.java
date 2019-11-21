package com.practice.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.demo.model.Logging;

public interface LoggingRepository extends JpaRepository<Logging, Integer> {

}
