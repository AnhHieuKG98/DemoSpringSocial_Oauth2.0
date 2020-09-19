package com.doan.admin.repo;

import com.doan.admin.model.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRoleRepo extends JpaRepository<EmployeeRole, Long> {

    EmployeeRole findByEmployeeCode(String employeeCode);
}
