package com.steereengineering.converter;

import org.springframework.stereotype.Component;

import com.steereengineering.command.PhoneListCommand;
import com.steereengineering.model.PhoneList;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

@Component
public class PhoneListCommandToPhoneList implements Converter<PhoneListCommand, PhoneList> {
	@Synchronized
	@Nullable
	@Override
	public PhoneList convert(PhoneListCommand source) {
		if (source == null) {
			return null;
		}

		final PhoneList pl = new PhoneList();
		pl.setId(source.getId());
		pl.setFirstname(source.getFirstname());
		pl.setLastname(source.getLastname());
		pl.setExtension(source.getExtension());
		pl.setEmail(source.getEmail());
		pl.setCellphone(source.getCellphone());
		return pl;
	}

}
