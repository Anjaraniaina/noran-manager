package me.noran.manager.model.utilities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DateRange {

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate begin;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate end;
}
