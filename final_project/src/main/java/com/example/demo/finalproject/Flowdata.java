package com.example.demo.finalproject;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Flowdata {
	@Id
	public String _id;

	private String flows;
	private String firstname;
	private String lastname;
	private String email;
}
