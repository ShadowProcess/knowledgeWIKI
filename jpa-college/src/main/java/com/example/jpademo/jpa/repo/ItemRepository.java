package com.example.jpademo.jpa.repo;

import com.example.jpademo.jpa.entity.manytomany.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Integer> {
}
