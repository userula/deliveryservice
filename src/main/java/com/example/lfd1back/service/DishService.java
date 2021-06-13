package com.example.lfd1back.service;

import com.example.lfd1back.model.Dish;
import com.example.lfd1back.repository.DishRepository;
import com.example.lfd1back.service.interfaces.IDishService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DishService implements IDishService {

    @Autowired
    private DishRepository dishRepository;

    @Override
    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    @Override
    public Dish getOne(Long id) {
        return dishRepository.getOne(id);
    }


}
