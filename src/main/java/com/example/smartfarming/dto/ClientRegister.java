package com.example.smartfarming.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRegister {

	String email;
	String password;
	String passwordCheck;
	String name;
	String city;
	String country;

}
