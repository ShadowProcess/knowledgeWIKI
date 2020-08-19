package com.example.jpademo.jpa.repo;

import com.example.jpademo.jpa.entity.local_date_time.LocalDateTimeCollege;
import com.example.jpademo.jpa.entity.manytomany.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LocalDateTimeRepository extends JpaRepository<LocalDateTimeCollege,Integer>, JpaSpecificationExecutor<LocalDateTimeCollege> {
}
