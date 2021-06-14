package com.example.lfd1back.repository;

import com.example.lfd1back.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    List<Dish> findDishesByRestaurantId(Long id);
}
