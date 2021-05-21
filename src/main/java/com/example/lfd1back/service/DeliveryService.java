package com.example.lfd1back.service;

import com.example.lfd1back.model.Cart;
import com.example.lfd1back.model.Deliveryman;
import com.example.lfd1back.repository.DeliveryRepository;
import com.example.lfd1back.service.interfaces.IDeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeliveryService implements IDeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public boolean save(Deliveryman deliveryman) {
        if(deliveryRepository.existsById(deliveryman.getId()))
        {
            return false;
        }
        deliveryRepository.save(deliveryman);
        return true;
    }

    @Override
    public boolean update(Deliveryman deliveryman) {
        return false;
    }

    @Override
    public boolean delete(Deliveryman deliveryman) {
        return false;
    }

    @Override
    public List<Deliveryman> getAll() {
        return deliveryRepository.findAll();
    }

    @Override
    public Deliveryman findById(Long id) {
        return deliveryRepository.getOne(id);
    }

    @Override
    public List<Deliveryman> getFreeDel(){
        return deliveryRepository.getFreeDel();
    }
}
