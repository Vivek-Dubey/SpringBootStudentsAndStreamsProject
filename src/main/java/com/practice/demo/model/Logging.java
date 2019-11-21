package com.practice.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Logging {
	
	@Id
	@GeneratedValue
	private int logId;
	private String URL;
	private String startTime;
	private String endTime;
	

}
