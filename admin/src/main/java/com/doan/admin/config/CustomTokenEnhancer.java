package com.doan.admin.config;

import com.doan.admin.dto.EmployeeDTO;
import com.doan.admin.model.Employee;
import com.doan.admin.model.EmployeeRole;
import com.doan.admin.model.User;
import com.doan.admin.repo.EmployeeRepo;
import com.doan.admin.repo.EmployeeRoleRepo;
import com.doan.admin.repo.UserRepo;
import com.doan.admin.service.EmployeeService;
import com.doan.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRoleRepo employeeRoleRepo;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        EmployeeDTO employeeDTO = employeeService.getInfoEmployee(oAuth2Authentication.getName());
        EmployeeRole employeeRole = employeeRoleRepo.findByEmployeeCode(employeeDTO.getCode());
        // User user = (User) oAuth2Authentication.getPrincipal();
        final Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("userInfo", employeeDTO);
        objectMap.put("authorities", employeeRole.getRoleName());
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(objectMap);
        return oAuth2AccessToken;
    }
}
