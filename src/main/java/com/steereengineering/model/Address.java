package com.steereengineering.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode(exclude = {"company"})
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String AddressLine1;
	private String AddressLine2;
	private String City;
	private String State;
	private String Zipode;
	private String Description;

	@ManyToOne
	private Company company;

	public Address() {
	}

	public Address(Long id, String addressLine1, String addressLine2, String city, String state, String zipode,
			String description) {
		super();
		this.id = id;
		AddressLine1 = addressLine1;
		AddressLine2 = addressLine2;
		City = city;
		State = state;
		Zipode = zipode;
		Description = description;
	}

	public Address(Long id, String addressLine1, String addressLine2, String city, String state, String zipode,
			String description, Company company) {
		super();
		this.id = id;
		AddressLine1 = addressLine1;
		AddressLine2 = addressLine2;
		City = city;
		State = state;
		Zipode = zipode;
		Description = description;
		this.company = company;
	}

}




