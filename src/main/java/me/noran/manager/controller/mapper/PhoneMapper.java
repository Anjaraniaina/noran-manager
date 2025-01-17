package me.noran.manager.controller.mapper;

import me.noran.manager.model.exception.BadRequestException;
import me.noran.manager.repository.PhoneRepository;
import me.noran.manager.repository.entity.Phone;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class PhoneMapper {
    private static final String JOIN_ELEMENT = ",";
    private PhoneRepository phoneRepository;

    public Phone toDomain(me.noran.manager.model.Phone fromView, String employeeId) {
        String valueFromView = createPhoneValue(fromView.getValue(), fromView.getCountryCode());

        if(fromView.getId() == null){
            boolean isPhoneAlreadyExist = phoneRepository.isPhoneAlreadyExist(valueFromView);
            if (isPhoneAlreadyExist){
                throw new BadRequestException("The phone " + fromView.getValue() + " already exist.");
            }
            return phoneRepository.save(Phone.builder().value(valueFromView).build());
        }
        else {
            Optional<Phone> existPhone = phoneRepository.findById(fromView.getId());
            if (existPhone.isPresent() && !existPhone.get().getEmployee().getId().equals(employeeId)){
                throw new BadRequestException("The phone " + fromView.getValue() + " already used by another employee.");
            }
            return phoneRepository.save(Phone.builder().id(fromView.getId()).value(valueFromView).build());
        }
    }

    public me.noran.manager.model.Phone toView(Phone fromDomain) {
        return me.noran.manager.model.Phone.builder()
                .id(fromDomain.getId())
                .countryCode(getViewCountryCode(fromDomain.getValue()))
                .value(getViewValue(fromDomain.getValue()))
                .build();
    }

    public String createPhoneValue(String value, String countryCode) {
        return countryCode + JOIN_ELEMENT + value;
    }

    public String getViewValue(String value) {
        return value.split(JOIN_ELEMENT)[1];
    }

    public String getViewCountryCode(String value) {
        return value.split(JOIN_ELEMENT)[0];
    }
}
