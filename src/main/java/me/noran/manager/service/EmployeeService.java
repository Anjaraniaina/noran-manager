package me.noran.manager.service;

import me.noran.manager.cnapsrepo.EmployeeCnapsRepository;
import me.noran.manager.cnapsrepo.entity.EmployeeCnapsEntity;
import me.noran.manager.model.EmployeeFilter;
import me.noran.manager.model.enums.BirthDateOption;
import me.noran.manager.model.exception.NotFoundException;
import me.noran.manager.repository.EmployeeInternRepository;
import me.noran.manager.repository.EmployeeRepositoryFacade;
import me.noran.manager.repository.dao.EmployeeManagerDao;
import me.noran.manager.repository.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeInternRepository repository;
    private EmployeeCnapsRepository employeeCnapsRepository;
    private EmployeeRepositoryFacade employeeRepositoryFacade;


    public Employee getOne(String id) {
        return employeeRepositoryFacade.findOne(id);
    }

    public Employee getOneByAgeOption(String id, BirthDateOption ageOption) {
        return employeeRepositoryFacade.findOne(id);
    }

    public List<Employee> getAll(EmployeeFilter filter) {
        return employeeRepositoryFacade.findAll(filter);
    }

    public void saveOne(Employee employee) {
        repository.save(employee);
    }
}
