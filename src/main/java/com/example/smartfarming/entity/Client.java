package com.example.smartfarming.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Client {

	@Id
	String id;
	@Column(unique = true)
	String email;
	String password;
	String name;
	String country;
	String city;

}
