package com.steereengineering.command;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class PhoneListCommand {
	private Long id;
	@Size(min=1, max = 255)
	private String firstname;
	@Size(min=1, max = 255)
	private String lastname;
	@Size(min=3, max = 255)
	@Email
	private String email;
	private String extension;
	private String cellphone;
}
