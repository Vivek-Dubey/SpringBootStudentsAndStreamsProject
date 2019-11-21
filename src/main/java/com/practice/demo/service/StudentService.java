package com.practice.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.demo.model.Student;
import com.practice.demo.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	

	public List<Student> getStudentsByStream(int streamId) {
		List<Student> students = new ArrayList<>();
		studentRepository.findByStreamStreamId(streamId).forEach(students::add);
		return students;
	}

	public Student getstudent(int streamId, int id) {
		List<Student> students=getStudentsByStream(streamId);
		for (int i=0; i<students.size(); i++) {
			Student s = students.get(i);
			if(s.getStudentId()==id) {
				return s;
			}
		}
		return null;
	}

	public void addStudent(Student student) {
		studentRepository.save(student);
	}

	public void updateStudent(Student student, int id) {
		Student st=studentRepository.findByStudentId(id);
		if(st!=null) {
			st.setStudentName(student.getStudentName());
			st.setStudentEmail(student.getStudentEmail());
			studentRepository.save(st);			}
			else {
				System.out.println("Student with Id :"+ id +" is not available");
			}
		
	}

	public void deleteStudent(int streamId, int id) {
		List<Student> students=getStudentsByStream(streamId);
		for (int i=0; i<students.size(); i++) {
			Student s = students.get(i);
			if(s.getStudentId()==id) {
				studentRepository.deleteById(id);
			}
		}
	}

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public List<Student> getPagedStudents(int start, int size) {
		
		List<Student> students = studentRepository.findAll();

		if((start+size) > students.size()) {
			return students.subList(start,students.size());
		}
		else if(start>students.size() || size==0) {
			return null;
		}
		
		return students.subList(start, start+size);
	}

}
