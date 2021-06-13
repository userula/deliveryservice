package com.example.lfd1back.repository;

import com.example.lfd1back.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {

}
