package com.example.lfd1back.controller;

import com.example.lfd1back.model.Dish;
import com.example.lfd1back.model.Restaurant;
import com.example.lfd1back.service.interfaces.IRestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class RestaurantController {
    @Autowired
    private final IRestaurantService restaurantService;

    @GetMapping("/allrestaurants")
    public String getAll(Model model){
        List<Restaurant> l = restaurantService.getAll();
        model.addAttribute("restaurants", l);
        return "restaurants";
    }

    @PostMapping("/createRestaurant")
    public String add(@RequestParam(value = "resName") String name, @RequestParam(value = "resLocation") String loc){
        Restaurant r = new Restaurant();
        r.setName(name);
        r.setLocation(loc);

        restaurantService.add(r);

        return "redirect:/allrestaurants";
    }

    @RequestMapping("/showrestdishes")
    public String show(@RequestParam(value = "resnameforinp") String id, Model model){
        Long resid = Long.parseLong(id);

        List<Dish> dishes = restaurantService.findDishesById(resid);

        Restaurant restaurant = restaurantService.findById(resid);

        model.addAttribute("dishes", dishes);
        model.addAttribute("res", restaurant);

        return "showrestaurantdishes";

    }

}
