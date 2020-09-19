package com.doan.admin.service;

import com.doan.admin.model.User;

import java.util.List;

public interface UserService {

    String save(User user) throws Exception;

    List<User> findAll() throws Exception;

    User findOne(long id) throws Exception;

    void delete(long id) throws Exception;
}
