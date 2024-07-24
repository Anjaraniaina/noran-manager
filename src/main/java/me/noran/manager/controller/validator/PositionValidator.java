package me.noran.manager.controller.validator;

import me.noran.manager.controller.validator.utils.StringValidator;
import me.noran.manager.model.exception.BadRequestException;
import me.noran.manager.repository.entity.Position;

public class PositionValidator {
    public void validate(Position position){
        StringBuilder error = new StringBuilder();

        if(StringValidator.isNotNullAndNotBlank(position.getName())){
            error.append("Position name is mandatory");
        }

        if(!error.isEmpty()){
            throw new BadRequestException(error.toString());
        }
    }
}
