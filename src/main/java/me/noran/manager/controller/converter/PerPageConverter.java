package me.noran.manager.controller.converter;

import me.noran.manager.model.utilities.PerPage;
import org.springframework.core.convert.converter.Converter;

public class PerPageConverter implements Converter<String, PerPage> {
    @Override
    public PerPage convert(String source) {
        return new PerPage(Integer.valueOf(source));
    }
}
