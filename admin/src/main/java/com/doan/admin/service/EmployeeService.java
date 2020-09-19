package com.doan.admin.service;

import com.doan.admin.dto.EmployeeDTO;
import com.doan.admin.model.Employee;

import java.util.List;

public interface EmployeeService {

    String insertEmployee(Employee employee) throws Exception;

    List<EmployeeDTO> getAllEmployee() throws Exception;

    List<Employee> getManage() throws Exception;

    EmployeeDTO getInfoEmployee(String email);

    String resetPass(Employee employee) throws Exception;

    String editEmployee(Employee employee) throws Exception;

    String deleteEmployee(Long id) throws Exception;

    List<EmployeeDTO> searchEmployee(EmployeeDTO employeeDTO) throws Exception;
}
