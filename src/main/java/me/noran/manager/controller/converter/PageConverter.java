package me.noran.manager.controller.converter;

import me.noran.manager.model.utilities.Page;
import org.springframework.core.convert.converter.Converter;

public class PageConverter implements Converter<String, Page> {
    @Override
    public Page convert(String source) {
        return new Page(Integer.valueOf(source));
    }
}
