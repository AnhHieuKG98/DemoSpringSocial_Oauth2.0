package com.doan.admin.service.impl;

import com.doan.admin.dto.CategoryDTO;
import com.doan.admin.helper.Contains;
import com.doan.admin.model.Category;
import com.doan.admin.repo.CategoryRepo;
import com.doan.admin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<CategoryDTO> getListCategory() throws Exception {
        List<Category> list = categoryRepo.findAllByOrderByIdDesc();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for(Category category : list) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(category.getId());
            categoryDTO.setCategoryName(category.getCategoryName());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String date = simpleDateFormat.format(category.getCreateDate());
            categoryDTO.setCreateDate(date);
            categoryDTOList.add(categoryDTO);
        }
        // ma hoa chuoi sang base64
            System.out.println(passwordEncoder.encode("chungphuc"));

        return categoryDTOList;
    }

    @Override
    public String insertCategory(Category category) throws Exception {
        String message;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = simpleDateFormat.format(new Date());
        Date date = simpleDateFormat.parse(s);
        category.setCreateDate(date);
        categoryRepo.save(category);
        message = Contains.SUCCESS;
        return message;
    }

    @Override
    public String updateCategory(Category category) throws Exception {
        String message;
        categoryRepo.updateCategory(category.getCategoryName(), category.getId());
        message = Contains.SUCCESS;
        return message;
    }

    @Override
    public String deleteCategory(Category category) throws Exception {
        String message;
        categoryRepo.deleteById(category.getId());
        message = Contains.SUCCESS;
        return message;
    }
}
