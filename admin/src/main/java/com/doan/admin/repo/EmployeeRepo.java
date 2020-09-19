package com.doan.admin.repo;

import com.doan.admin.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>, EmployeeCustomRepo {

//    Employee findByEmailOrPhoneNumber(String text);

    Employee findByCode(String code);
}
