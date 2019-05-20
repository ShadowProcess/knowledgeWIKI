package com.example.jpademo.jpa.repo;

import com.example.jpademo.jpa.entity.manytomany.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
