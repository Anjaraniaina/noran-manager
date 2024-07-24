package me.noran.manager.repository;

import me.noran.manager.repository.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeInternRepository extends JpaRepository<Employee, String> {
}
