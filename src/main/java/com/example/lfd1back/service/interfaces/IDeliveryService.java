package com.example.lfd1back.service.interfaces;

import com.example.lfd1back.model.Cart;
import com.example.lfd1back.model.Deliveryman;
import com.example.lfd1back.model.User;

import java.util.List;

public interface IDeliveryService {
    boolean save(Deliveryman deliveryman);
    boolean update(Deliveryman deliveryman);
    boolean delete(Deliveryman deliveryman);
    List<Deliveryman> getAll();
    Deliveryman findById(Long id);
    List<Deliveryman> getFreeDel();
}
