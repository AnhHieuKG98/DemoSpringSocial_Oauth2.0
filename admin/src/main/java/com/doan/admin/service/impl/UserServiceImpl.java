package com.doan.admin.service.impl;

import com.doan.admin.helper.Contains;
import com.doan.admin.model.User;
import com.doan.admin.repo.UserRepo;
import com.doan.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        User user = userRepo.findByUserName(userName);
//        if(user == null){
//            throw new UsernameNotFoundException("Invalid username or password.");
//        }
//        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), getAuthority());
//    }
//
//    private List<SimpleGrantedAuthority> getAuthority() {
//        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
//    }

    @Override
    public String save(User user) throws Exception {
        String message;
        User newUser = userRepo.save(user);
        message = Contains.SUCCESS;
        return message;
    }

    @Override
    public List<User> findAll() throws Exception {
        List<User> list = new ArrayList<>();
        userRepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findOne(long id) throws Exception {
        return userRepo.findById(id).get();
    }

    @Override
    public void delete(long id) throws Exception {
        userRepo.deleteById(id);
    }
}
