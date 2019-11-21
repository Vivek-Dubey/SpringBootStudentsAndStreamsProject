package com.practice.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.demo.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	public List<Student> findByStreamStreamId(int streamId);

	public Student findByStudentId(int id);

}
