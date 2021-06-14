package com.example.lfd1back.service.interfaces;

import com.example.lfd1back.model.Dish;
import com.example.lfd1back.model.Restaurant;

import java.util.List;

public interface IRestaurantService {

    List<Restaurant> getAll();

    Restaurant findByName(String r);

    void add(Restaurant r);

    List<Dish> findDishesById(Long id);

    Restaurant findById(Long id);
}
