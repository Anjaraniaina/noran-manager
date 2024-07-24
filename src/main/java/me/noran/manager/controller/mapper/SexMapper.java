package me.noran.manager.controller.mapper;

import me.noran.manager.repository.entity.enums.Sex;
import org.springframework.stereotype.Component;

@Component
public class SexMapper {
    public Sex toDomain(String sex){
        try {
            return Sex.valueOf(sex);
        } catch (IllegalArgumentException e){
            return null;
        }
    }
}
