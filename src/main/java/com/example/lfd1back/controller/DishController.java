package com.example.lfd1back.controller;

import com.example.lfd1back.model.Dish;
import com.example.lfd1back.model.Restaurant;
import com.example.lfd1back.service.interfaces.IDishService;
import com.example.lfd1back.service.interfaces.IRestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class DishController {
    @Autowired
    private final IDishService dishService;

    @Autowired
    private final IRestaurantService restaurantService;

    @PostMapping("/createDish")
    public String add(@RequestParam(value = "dishName") String name, @RequestParam(value = "dishPrice") String price, @RequestParam(value = "restaurant") String restaurant){

        restaurant = restaurant.substring(restaurant.indexOf('-') + 1, restaurant.length());
//        System.out.println(restaurant);
        Restaurant r = restaurantService.findByName(restaurant);

        Dish dish = new Dish();
        dish.setName(name);
        dish.setPrice(price);
        dish.setRestaurantId(r.getId());
        dishService.add(dish);
        return "redirect:/userprof";
    }
}
