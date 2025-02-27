package com.ctw.workstation.repositories;

import com.ctw.workstation.booking.entity.Booking;
import com.ctw.workstation.booking.entity.BookingReturnDTO;
import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.rackasset.entity.RackAssetReturnDTO;
import com.ctw.workstation.team.entity.Team;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;

@ApplicationScoped
public class RackRepository implements PanacheRepository<Rack> {

    public Collection<Rack> getAll(){
        return findAll().stream().toList();
    }

    public Rack getById(Long id){
        return find("id", id).firstResult();
    }

    public Rack getBySerialNumber(String serialNumber) {
        return find("serialNumber", serialNumber).firstResult();
    }

    public BookingReturnDTO[] getBookings(long id) {
        Rack r = getById(id);
        return r.getBookings();
    }

    public RackAssetReturnDTO[] getRackAssets(long id) {
        Rack r = getById(id);
        return r.getRackAssets();
    }
}
