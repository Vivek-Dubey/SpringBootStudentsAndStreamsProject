package com.practice.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.demo.model.Stream;
import com.practice.demo.model.Student;
import com.practice.demo.service.LoggingService;
import com.practice.demo.service.StudentService;

@RestController
public class StudentController {
	
	Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private LoggingService loggingService;
	
	String startTime;
	
	@GetMapping("student/getAll")
	public List<Student> getAllStudents(HttpServletRequest request,@RequestParam(name = "start",required = false,defaultValue = "0") int start,
			@RequestParam(name = "size", required = false,defaultValue = "0") int size){
		
		logger.trace("All students returned");
		
		startTime = LocalDateTime.now().toString();
		
		if(start > 0 && size > 0) {
			
			List<Student> result = studentService.getPagedStudents(start-1, size);
			
			loggingService.saveLog(startTime,request.getRequestURI());
			
			return result;
			
		}
		List<Student> result=studentService.getAllStudents();
		
		loggingService.saveLog(startTime,request.getRequestURI());
		
		return result;
	}
	
	@GetMapping("/stream/{streamId}/student/getAll")
	public List<Student> getStudentsByStream(@PathVariable int streamId, HttpServletRequest request){
		
		logger.trace("All students returned with StreamId: "+streamId);
		
		startTime = LocalDateTime.now().toString();
		
		List<Student> result=studentService.getStudentsByStream(streamId);
		
		loggingService.saveLog(startTime,request.getRequestURI());
		
		return result;
	}
	
	@GetMapping("/stream/{streamId}/student/get/{id}")
	public Student getstudent(@PathVariable int streamId,  @PathVariable int id, HttpServletRequest request)
	{
		logger.trace("Student retuned with streamId: "+streamId+" and id: "+id);
		
		startTime = LocalDateTime.now().toString();
		
		Student student = studentService.getstudent(streamId,id);
		
		loggingService.saveLog(startTime,request.getRequestURI());
		
		return student;
	}
	
	@PostMapping(value = "/stream/{streamId}/student/add")
	public void addStudent(@RequestBody Student student, @PathVariable int streamId ,HttpServletRequest request) {
		
		logger.trace("New student aaded with streamId: "+streamId);

		startTime = LocalDateTime.now().toString();
		
		student.setStream(new Stream(streamId,""));
		studentService.addStudent(student);
		
		loggingService.saveLog(startTime,request.getRequestURI());
	}
	
	@PutMapping(value = "/stream/{streamId}/student/update/{id}")
	public void updateStudent(@RequestBody Student student, @PathVariable int streamId, @PathVariable int id ,HttpServletRequest request) {
		
		logger.trace("Student updated with Streamid: "+streamId+ " and id: "+id);

		startTime = LocalDateTime.now().toString();
		
		student.setStream(new Stream(streamId,""));
		studentService.updateStudent(student,id);
		
		loggingService.saveLog(startTime,request.getRequestURI());
	}
	
	@DeleteMapping(value = "/stream/{streamId}/student/delete/{id}")
	public void deleteStudent(@PathVariable int streamId, @PathVariable int id, HttpServletRequest request)
	{
		logger.trace("Student deleted with StreamId: "+streamId+" and id: "+id);
		
		startTime = LocalDateTime.now().toString();
		
		studentService.deleteStudent(streamId, id);
		
		loggingService.saveLog(startTime,request.getRequestURI());
	}
	
}
