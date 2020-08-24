package com.example.jpademo.jpa.repo;

import com.example.jpademo.jpa.entity.enum_type.EnumTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EnumRepository extends JpaRepository<EnumTable,Long>, JpaSpecificationExecutor<EnumTable> {
}
