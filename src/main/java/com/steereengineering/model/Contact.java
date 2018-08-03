package com.steereengineering.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode(exclude = {"company"})

public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	@NotNull
	private String firstname;
	@NotNull 
	private String lastname;
	@Column(unique=true)
	private String email;
	
	@ManyToOne
	private Company company;
	
	public Contact() {
	}
	
	
	
	
	
}
