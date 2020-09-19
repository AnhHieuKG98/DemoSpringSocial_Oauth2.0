package com.doan.admin.service;

import com.doan.admin.dto.CategoryDTO;
import com.doan.admin.model.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getListCategory() throws Exception;

    String insertCategory(Category category) throws Exception;

    String updateCategory(Category category) throws Exception;

    String deleteCategory(Category category) throws Exception;
}
