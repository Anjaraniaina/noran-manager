package com.example.prog4.repository;

import com.example.prog4.cnapsrepo.EmployeeCnapsRepository;
import com.example.prog4.cnapsrepo.entity.EmployeeCnapsEntity;
import com.example.prog4.model.EmployeeFilter;
import com.example.prog4.model.exception.NotFoundException;
import com.example.prog4.repository.dao.EmployeeManagerDao;
import com.example.prog4.repository.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Repository
@Slf4j
public class EmployeeRepositoryFacade implements EmployeeRepository {

    private final EmployeeInternRepository employeeInternRepository;
    private final EmployeeCnapsRepository employeeCnapsRepository;
    private EmployeeManagerDao employeeManagerDao;

    @Override
    public Employee findOne(String id) {
        Employee employeeInter = employeeInternRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found id=" + id));
        String endToEndId = employeeInter.getEndToEndId();
        if(endToEndId.isEmpty() || endToEndId == null)
            return employeeInter;
        else {
            EmployeeCnapsEntity employeeCnaps = employeeCnapsRepository.findById(employeeInter.getEndToEndId()).orElseThrow(() -> new NotFoundException("Not found in cnaps databaseid=" + employeeInter.getEndToEndId()));
            return employeeInter.toBuilder().cnaps(employeeCnaps.getCnaps()).build();
        }
    }

    @Override
    public List<Employee> findAll(EmployeeFilter filter) {
        Sort sort = Sort.by(filter.getOrderDirection(), filter.getOrderBy().toString());
        Pageable pageable = PageRequest.of(filter.getIntPage() - 1, filter.getIntPerPage(), sort);
        return employeeManagerDao.findByCriteria(filter.getLastName(), filter.getFirstName(), filter.getCountryCode(), filter.getSex(), filter.getPosition(), filter.getEntrance(), filter.getDeparture(), pageable)
                .stream().map(this::combine).toList();
    }


    // intern end_to_end_id equals cnaps employee id
    public Employee combine(Employee intern) {
        String endToEndId = intern.getEndToEndId();
        if(endToEndId.isEmpty() || endToEndId == null)
            return intern;
        else {
            EmployeeCnapsEntity cnapsEntity = employeeCnapsRepository.findById(intern.getEndToEndId()).orElseThrow(() -> new NotFoundException("Not found in cnaps database id=" + intern.getEndToEndId()));
            return intern.toBuilder().cnaps(cnapsEntity.getCnaps()).build();
        }
    }
}
