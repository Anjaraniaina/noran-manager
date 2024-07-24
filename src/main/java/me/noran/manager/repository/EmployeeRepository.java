package me.noran.manager.repository;

import me.noran.manager.model.EmployeeFilter;
import me.noran.manager.repository.entity.Employee;

import java.util.List;

public interface EmployeeRepository {
    Employee findOne(String id);

    List<Employee> findAll(EmployeeFilter filter);
}
