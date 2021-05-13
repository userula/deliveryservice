package com.example.lfd1back.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Dish dish;

    private User customer;

}
