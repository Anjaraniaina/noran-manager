package me.noran.manager.model.utilities;

import me.noran.manager.model.exception.BadRequestException;
import lombok.Getter;

public record Page(@Getter Integer page) {
    public Page(Integer page) {
        if (page < 1) {
            throw new BadRequestException("Page cannot be < 1");
        } else {
            this.page = page - 1;
        }
    }
}
