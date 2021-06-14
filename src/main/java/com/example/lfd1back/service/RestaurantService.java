package com.example.lfd1back.service;

import com.example.lfd1back.model.Dish;
import com.example.lfd1back.model.Restaurant;
import com.example.lfd1back.repository.DishRepository;
import com.example.lfd1back.repository.RestaurantRepository;
import com.example.lfd1back.service.interfaces.IRestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantService implements IRestaurantService {

    @Autowired
    private final RestaurantRepository restaurantRepository;

    @Autowired
    private final DishRepository dishRepository;

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant findByName(String r) {
        return restaurantRepository.findByName(r);
    }

    @Override
    public void add(Restaurant r) {
        restaurantRepository.save(r);
    }

    @Override
    public List<Dish> findDishesById(Long id) {
        return dishRepository.findDishesByRestaurantId(id);
    }

    @Override
    public Restaurant findById(Long id) {
        return restaurantRepository.getById(id);
    }
}
