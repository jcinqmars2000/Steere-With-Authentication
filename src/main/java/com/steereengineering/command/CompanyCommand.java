package com.steereengineering.command;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import com.steereengineering.model.CompanyType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class CompanyCommand {

	private Long id;
	private String name;

	private Set<AddressCommand> adresses = new HashSet<>();
	private CompanyType companytype;
	private Set<ContactCommand> contact = new HashSet<>();

}
