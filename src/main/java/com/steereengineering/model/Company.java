package com.steereengineering.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity

public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String companytype;
	
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
	private Set<Address> address = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
	private Set<Contact> contact = new HashSet<>();
    
    

     public Company addAddress(Address address) {
    	 address.setCompany(this);
    	 this.address.add(address);
    	 return this;
     }

     public Company addContact(Contact contact) {
    	 contact.setCompany(this);
    	 this.contact.add(contact);
    	 return this;
     }


}
