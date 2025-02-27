package com.ctw.workstation.repositories;

import com.ctw.workstation.booking.entity.Booking;
import com.ctw.workstation.booking.entity.Booking;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;

@ApplicationScoped
public class BookingRepository implements PanacheRepository<Booking> {
    public Collection<Booking> getAll(){
        return findAll().stream().toList();
    }

    public Booking getById(Long id){
        return find("id", id).firstResult();
    }
}
