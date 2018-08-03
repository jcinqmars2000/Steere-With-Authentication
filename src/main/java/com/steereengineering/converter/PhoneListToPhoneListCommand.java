package com.steereengineering.converter;


import com.steereengineering.command.PhoneListCommand;
import com.steereengineering.model.PhoneList;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class PhoneListToPhoneListCommand implements Converter<PhoneList, PhoneListCommand> {
	@Synchronized
	@Nullable
	@Override
	public PhoneListCommand convert(PhoneList phoneList) {

		if (phoneList != null) {
			final PhoneListCommand plc = new PhoneListCommand();
			plc.setId(phoneList.getId());
			plc.setFirstname(phoneList.getFirstname());
			plc.setLastname(phoneList.getLastname());
			plc.setEmail(phoneList.getEmail());
			plc.setExtension(phoneList.getExtension());
			plc.setCellphone(phoneList.getCellphone());

			return plc;
		}
		return null;
	}
}

