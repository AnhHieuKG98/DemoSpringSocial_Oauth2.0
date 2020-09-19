package com.doan.admin.service.impl;

import com.doan.admin.dto.EmployeeDTO;
import com.doan.admin.helper.Contains;
import com.doan.admin.helper.DataUtil;
import com.doan.admin.model.Employee;
import com.doan.admin.model.EmployeeRole;
import com.doan.admin.repo.EmployeeRepo;
import com.doan.admin.repo.EmployeeRoleRepo;
import com.doan.admin.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service(value = "employeeService")
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private EmployeeRoleRepo employeeRoleRepo;

    @Override
    public UserDetails loadUserByUsername(String text) throws UsernameNotFoundException {
        Employee employee = employeeRepo.getByEmailOrPhoneNumber(text);
        // ma hoa mat khau
        convertPassword("nguyenchung");
        if(employee == null) {
            throw new UsernameNotFoundException("Valid User Name Or Password");
        }
        if (!DataUtil.isNullOrEmpty(employee.getEmail())) {
            return new User(employee.getEmail(), employee.getPassword(), getAuthority(employee.getCode()));
        }
        else {
            return new User(employee.getPhoneNumber(), employee.getPassword(), getAuthority(employee.getCode()));
        }
    }

    private void convertPassword(String password) {
        System.out.println(passwordEncoder.encode(password));
    }

    private List<SimpleGrantedAuthority> getAuthority(String employeeCode) {
        EmployeeRole employeeRole = employeeRoleRepo.findByEmployeeCode(employeeCode);
        String role = employeeRole.getRoleName();
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String insertEmployee(Employee employee) throws Exception {
        String message;
        Employee employeeOld = new Employee();
        if (!DataUtil.isNullOrEmpty(employee.getEmail())) {
            employeeOld = employeeRepo.getByEmailOrPhoneNumber(employee.getEmail());
        }
        if (!DataUtil.isNullOrEmpty(employee.getPhoneNumber())) {
            employeeOld = employeeRepo.getByEmailOrPhoneNumber(employee.getPhoneNumber());
        }
        if (employeeOld != null){
            message = Contains.EMAIL_EXIST;
        } else {
            //set code cho employee
            Object[] lastEmployee = employeeRepo.getLastRow();
            String codeEmployee = DataUtil.safeToString(lastEmployee[1]);
            String[] code = codeEmployee.split("-");
            employee.setCode(code[0] + "-" + (Integer.parseInt(code[1]) + 1));
            String password;
            if (!DataUtil.isNullOrEmpty(employee.getPassword())) {
                password = passwordEncoder.encode(employee.getPassword());
            } else {
                password = passwordEncoder.encode("1");
            }
            employee.setPassword(password);
            Employee employeeNew = employeeRepo.save(employee);
            EmployeeRole employeeRole = new EmployeeRole();
            employeeRole.setEmployeeCode(employeeNew.getCode());
            employeeRole.setRoleName(Contains.ROLE_USER);
            employeeRoleRepo.save(employeeRole);
            message = Contains.SUCCESS;
        }
        return message;
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() throws Exception {
        List<Employee> list = employeeRepo.findAll();
        List<EmployeeDTO> data = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for(Employee employee : list) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setCode(employee.getCode());
            employeeDTO.setEmail(employee.getEmail());
            employeeDTO.setPassword(employee.getPassword());
            employeeDTO.setFirstName(employee.getFirstName());
            employeeDTO.setLastName(employee.getLastName());
            employeeDTO.setPhoneNumber(employee.getPhoneNumber());
            data.add(employeeDTO);
        }
        return data;
    }

    @Override
    public List<Employee> getManage() throws Exception {
        List<Employee> list = employeeRepo.findAll();
        List<Employee> employeeList = new ArrayList<>();
        for(Employee employee : list) {
            Employee employeeNew = new Employee();
            employeeNew.setCode(employee.getCode());
            employeeNew.setFirstName(employee.getFirstName());
            employeeNew.setLastName(employee.getLastName());
            employeeList.add(employeeNew);
        }
        return employeeList;
    }

    @Override
    public EmployeeDTO getInfoEmployee(String email) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Employee employee = employeeRepo.getByEmailOrPhoneNumber(email);
        employeeDTO.setId(employee.getId());
        employeeDTO.setCode(employee.getCode());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setPassword(employee.getPassword());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setPhoneNumber(employee.getPhoneNumber());
        return employeeDTO;
    }

    @Override
    public String resetPass(Employee employee) throws Exception {
        String message;
        Employee employeeOld = new Employee();
        if (!DataUtil.isNullOrEmpty(employee.getEmail())) {
            employeeOld = employeeRepo.getByEmailOrPhoneNumber(employee.getEmail());
        }
        if (!DataUtil.isNullOrEmpty(employee.getPhoneNumber())) {
            employeeOld = employeeRepo.getByEmailOrPhoneNumber(employee.getPhoneNumber());
        }
        if (employee != null) {
            employee.setCode(employeeOld.getCode());
            employee.setLastName(employeeOld.getLastName());
            employee.setFirstName(employeeOld.getFirstName());
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            employeeRepo.updateEmployee(employee);
            message = Contains.SUCCESS;
        } else {
            message = Contains.EMAIL_NOT_EXIST;
        }
        return message;
    }

    @Override
    public String editEmployee(Employee employee) throws Exception {
        String message = null;
        Employee employeeOld = new Employee();
        if (!DataUtil.isNullOrEmpty(employee.getEmail())) {
            employeeOld = employeeRepo.getByEmailOrPhoneNumber(employee.getEmail());
        }
        if (!DataUtil.isNullOrEmpty(employee.getPhoneNumber())) {
            employeeOld = employeeRepo.getByEmailOrPhoneNumber(employee.getPhoneNumber());
        }
        if (employeeOld != null) {
            employee.setCode(employeeOld.getCode());
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            employeeRepo.updateEmployee(employee);
            message = Contains.SUCCESS;
        }
        return message;
    }

    @Override
    public String deleteEmployee(Long id) throws Exception {
        String message = null;
        if (!DataUtil.isNullOrZero(id)) {
            Employee employee = employeeRepo.findById(id).get();
            String employeeCode = employee.getCode();
            EmployeeRole employeeRole = employeeRoleRepo.findByEmployeeCode(employeeCode);
            employeeRoleRepo.deleteById(employeeRole.getId());
            employeeRepo.deleteById(id);
            message = Contains.SUCCESS;
        }
        return message;
    }

    @Override
    public List<EmployeeDTO> searchEmployee(EmployeeDTO employeeDTO) throws Exception {
        List<EmployeeDTO> list = new ArrayList<>();
        List<Object[]> listData = employeeRepo.searchEmployee(employeeDTO);
        for(Object[] employee : listData) {
            EmployeeDTO data = new EmployeeDTO();
            data.setId(DataUtil.safeToLong(employee[0]));
            data.setCode(DataUtil.safeToString(employee[1]));
            data.setEmail(DataUtil.safeToString(employee[2]));
            data.setFirstName(DataUtil.safeToString(employee[4]));
            data.setLastName(DataUtil.safeToString(employee[5]));
            data.setPhoneNumber(DataUtil.safeToString(employee[6]));
            list.add(data);
        }
        return list;
    }
}
