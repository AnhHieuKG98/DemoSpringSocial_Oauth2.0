package com.doan.admin.controller;

import com.doan.admin.dto.EmployeeDTO;
import com.doan.admin.helper.ApiResponse;
import com.doan.admin.model.Employee;
import com.doan.admin.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/*
 *   author: HungNN
 */

@RestController
@RequestMapping("${api_base_path}/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/newEmployee")
    public ApiResponse insertEmployee(@RequestBody Employee employee) {
        try {
            String message = employeeService.insertEmployee(employee);
            return ApiResponse.build(HttpServletResponse.SC_OK, true, "", message);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.build(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, false, e.getMessage(), null);
        }
    }

    @GetMapping("/getEmployee")
    public ApiResponse getAllEmployee() {
        try {
            List<EmployeeDTO> list = employeeService.getAllEmployee();
            return ApiResponse.build(HttpServletResponse.SC_OK, true, "", list);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.build(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, false, e.getMessage(), null);
        }
    }

    @GetMapping("/getManage")
    public ApiResponse getManage() {
        try {
            List<Employee> list = employeeService.getManage();
            return ApiResponse.build(HttpServletResponse.SC_OK, false, "", list);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.build(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, false, e.getMessage(), null);
        }
    }

    @PostMapping("/update")
    public ApiResponse checkEmail(@RequestBody Employee employee) {
        try {
            String message = employeeService.editEmployee(employee);
            return ApiResponse.build(HttpServletResponse.SC_OK, true, "", message);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.build(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, false, e.getMessage(), null);
        }
    }

    @PostMapping("/reset-pass")
    public ApiResponse resetPassword(@RequestBody Employee employee) {
        try {
            String message = employeeService.resetPass(employee);
            return ApiResponse.build(HttpServletResponse.SC_OK, true, "", message);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.build(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, false, e.getMessage(), null);
        }
    }

    @PostMapping("/delete")
    public ApiResponse deleteEmployee(@RequestBody EmployeeDTO employee) {
        try {
            String message = employeeService.deleteEmployee(employee.getId());
            return ApiResponse.build(HttpServletResponse.SC_OK, true, "", message);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.build(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, false, e.getMessage(), null);
        }
    }

    @PostMapping("/search")
    public ApiResponse searchEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try {
            List<EmployeeDTO> list = employeeService.searchEmployee(employeeDTO);
            return ApiResponse.build(HttpServletResponse.SC_OK, true, "", list);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.build(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, false, e.getMessage(), null);
        }
    }
}
