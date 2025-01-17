package me.noran.manager.controller.validator;

import me.noran.manager.model.Phone;
import me.noran.manager.model.exception.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PhoneValidator {
    public void validate(Phone phone) {
        StringBuilder error = new StringBuilder();

        if (phone.getCountryCode() == null) {
            error.append("Phone's country code is mandatory. ");
        }
        
        if (phone.getValue() == null) {
            error.append("Phone's number is mandatory. ");
        } else if (phone.getValue().length() != 9) {
            error.append("Phone's number length must be = 9. ");
        } else if (phone.getValue().matches("[^0-9]")) {
            error.append("Phone's number must only contains digits. ");
        }

        if (phone.getCountryCode() == null) {
            error.append("Phone's country code is mandatory. ");
        } else if (phone.getCountryCode().length() < 1 || phone.getCountryCode().length() > 4) {
            error.append("Phone's country code length must be > 0 and < 5. ");
        } else if (phone.getCountryCode().matches("[^0-9]")) {
            error.append("Phone's country code must only contains digits. ");
        }

        if (!error.isEmpty()) {
            throw new BadRequestException(error.toString());
        }
    }
}
