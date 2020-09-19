package com.doan.admin.repo.impl;

import com.doan.admin.dto.EmployeeDTO;
import com.doan.admin.helper.DataUtil;
import com.doan.admin.model.Employee;
import com.doan.admin.repo.EmployeeCustomRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/*
 *   author: HungNN
 */

public class EmployeeCustomRepoImpl implements EmployeeCustomRepo {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Object[] getLastRow() throws Exception {
        Query query = entityManager.createNativeQuery("SELECT * FROM employee ORDER BY id desc LIMIT 1");
        return (Object[]) query.getSingleResult();
    }


    @Transactional
    @Override
    public void updateEmployee(Employee employee) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE employee SET code = :code, password = :password, first_name = :firstName, last_name = :lastName," +
                "phone_number = :phoneNumber WHERE email = :email");
        Query query = entityManager.createNativeQuery(stringBuilder.toString());
        query.setParameter("code", employee.getCode());
        query.setParameter("password", employee.getPassword());
        query.setParameter("firstName", employee.getFirstName());
        query.setParameter("lastName", employee.getLastName());
        query.setParameter("phoneNumber", employee.getPhoneNumber());
        query.setParameter("email", employee.getEmail());
        query.executeUpdate();
    }

    @Override
    public Employee getByEmailOrPhoneNumber(String text) {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select e from Employee e where e.email = :email or phoneNumber = :phoneNumber");
            Query query = entityManager.createQuery(sql.toString());
            query.setParameter("email", text);
            query.setParameter("phoneNumber", text);
            return (Employee) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Object[]> searchEmployee(EmployeeDTO employeeDTO) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder();
        HashMap hashMap = new HashMap();
        sqlBuilder.append("select * from employee");
        sqlBuilder.append(appendEmployeeSearch(employeeDTO,hashMap));
        Query query = entityManager.createNativeQuery(sqlBuilder.toString());
        hashMap.forEach((k, v) -> {
            query.setParameter(k.toString(), v);
        });
        return query.getResultList();
    }

    public StringBuilder appendEmployeeSearch(EmployeeDTO employeeDTO, HashMap hashMap) {
        StringBuilder sql = new StringBuilder();
        sql.append(" where 1 = 1");
        if (!DataUtil.isNullOrEmpty(employeeDTO.getCode())) {
            sql.append(" and code like :code");
            hashMap.put("code", "%" + employeeDTO.getCode() + "%");
        }
        if (!DataUtil.isNullOrEmpty(employeeDTO.getEmail())) {
            sql.append(" and email = :email");
            hashMap.put("email", employeeDTO.getEmail());
        }
        if (!DataUtil.isNullOrEmpty(employeeDTO.getFirstName())) {
            sql.append(" and first_name like :firstName");
            hashMap.put("firstName", "%" + employeeDTO.getFirstName() + "%");
        }
        if (!DataUtil.isNullOrEmpty(employeeDTO.getLastName())) {
            sql.append(" and last_name like :lastName");
            hashMap.put("lastName", "%" + employeeDTO.getLastName() + "%");
        }
        if (!DataUtil.isNullOrEmpty(employeeDTO.getPhoneNumber())) {
            sql.append(" and phone_number = :phoneNumber");
            hashMap.put("phoneNumber", employeeDTO.getPhoneNumber());
        }
        return sql;
    }
}
