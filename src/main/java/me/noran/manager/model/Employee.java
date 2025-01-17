package me.noran.manager.model;

import me.noran.manager.repository.entity.Position;
import me.noran.manager.repository.entity.enums.Csp;
import me.noran.manager.repository.entity.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
public class Employee implements Serializable {
    private String id;
    private String firstName;
    private String lastName;

    private MultipartFile image;
    private String stringImage;

    private Csp csp;
    private Sex sex;
    private String cin;
    private String endToEndId;
    private String address;
    private String cnaps;
    private Integer age;
    private Float salary;
    private Integer childrenNumber;
    private String personalEmail;
    private String professionalEmail;
    private String registrationNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate entranceDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

    private List<Position> positions;
    private List<Phone> phones;

}
