package com.example.lfd1back.repository;

import com.example.lfd1back.model.Deliveryman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface DeliveryRepository extends JpaRepository<Deliveryman, Long> {

    @Query(value = "SELECT * FROM deliveryman d WHERE d.isFree = 1", nativeQuery = true)
    List<Deliveryman> getFreeDel();

}
