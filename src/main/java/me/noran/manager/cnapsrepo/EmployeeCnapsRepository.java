package me.noran.manager.cnapsrepo;

import me.noran.manager.cnapsrepo.entity.EmployeeCnapsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeCnapsRepository extends JpaRepository<EmployeeCnapsEntity, String> {
}