package com.doan.admin.repo;

import com.doan.admin.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    List<Category> findAllByOrderByIdDesc();

    @Transactional
    @Modifying
    @Query(value = "update category SET category_name = :cateName where category_id = :id", nativeQuery = true)
    void updateCategory(@Param("cateName") String cateName, @Param("id") Long id);
}
