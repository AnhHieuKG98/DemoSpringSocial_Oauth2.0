package com.doan.admin.repo;

import com.doan.admin.dto.EmployeeDTO;
import com.doan.admin.model.Employee;

import java.util.List;

public interface EmployeeCustomRepo {

    Object[] getLastRow() throws Exception;

    void updateEmployee(Employee employee) throws Exception;

    Employee getByEmailOrPhoneNumber(String text);

    List<Object[]> searchEmployee(EmployeeDTO employeeDTO) throws Exception;
}
