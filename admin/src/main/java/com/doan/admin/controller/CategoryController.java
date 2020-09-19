package com.doan.admin.controller;

import com.doan.admin.dto.CategoryDTO;
import com.doan.admin.helper.ApiResponse;
import com.doan.admin.model.Category;
import com.doan.admin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/*
 *   author: HungNN
 */

@RestController
@RequestMapping("${api_base_path}/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAll")
    public ApiResponse getAllCate() throws Exception {
        try {
            List<CategoryDTO> list = categoryService.getListCategory();
            return ApiResponse.build(HttpServletResponse.SC_OK, true, "", list);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.build(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, false, e.getMessage(), null);
        }
    }

    @PostMapping("/insert")
    public ApiResponse insertCategory(@RequestBody Category category) {
        try {
            String message = categoryService.insertCategory(category);
            return ApiResponse.build(HttpServletResponse.SC_OK, true, "", message);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.build(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, false, e.getMessage(), null);
        }
    }

    @PostMapping("/delete")
    public ApiResponse deleteCategory(@RequestBody Category category){
        try {
            String message = categoryService.deleteCategory(category);
            return ApiResponse.build(HttpServletResponse.SC_OK, true, "", message);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.build(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, false, e.getMessage(), null);
        }
    }

    @PostMapping("/update")
    public ApiResponse updateCategory(@RequestBody Category category) {
        try {
            String message = categoryService.updateCategory(category);
            return ApiResponse.build(HttpServletResponse.SC_OK, true, "", message);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.build(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, false, e.getMessage(), null);
        }
    }
}
