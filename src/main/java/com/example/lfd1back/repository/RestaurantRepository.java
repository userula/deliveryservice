package com.example.lfd1back.repository;

import com.example.lfd1back.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Restaurant findByName(String name);

    Restaurant getById(Long id);
}
