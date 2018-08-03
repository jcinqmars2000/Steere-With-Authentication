package com.steereengineering.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class PhoneList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull 
	private String firstname;
	@NotNull 
	private String lastname;
	@Column(unique=true)
	private String email;
	private String extension;
	private String cellphone;

	
}
