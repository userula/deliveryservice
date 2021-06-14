package com.example.lfd1back.service.interfaces;

import com.example.lfd1back.model.Dish;

import java.util.List;

public interface IDishService {
    public List<Dish> getAll();

    public Dish getOne(Long id);

    public void add(Dish dish);
}
