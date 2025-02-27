package com.ctw.workstation.rack.entity;

import com.ctw.workstation.rackasset.entity.RackAssetReturnDTO;
import com.ctw.workstation.repositories.RackRepository;
import com.ctw.workstation.booking.entity.BookingReturnDTO;
import io.quarkus.arc.log.LoggerName;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;
import org.jboss.logging.MDC;

import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class RackService {

    @Inject
    RackRepository rackRepo;

    @Inject
    Logger log;

    @LoggerName("theOtherOne")
    Logger theOtherLog;

    public RackReturnDTO[] getAllRacks(){
        MDC.put("log", "Racks from DB");
        log.debug("Returning Racks from DB");
        theOtherLog.info("Returning Racks from DB... to the other logger!");

        Collection<Rack> racks = rackRepo.getAll();
        return racks.stream().map(Rack::toDTO).toList().toArray(new RackReturnDTO[racks.size()]);
    }

    public RackReturnDTO getRackById(Long rackId){
        return rackRepo.getById(rackId).toDTO();
    }

    public RackReturnDTO[] getRacksByStatus(String status){
        RackReturnDTO[] racks = this.getAllRacks();
        return Arrays.stream(racks)
                .filter(x -> x.status().equalsIgnoreCase(status)).toList().toArray(new RackReturnDTO[racks.length]);
    }

    @Transactional
    public RackReturnDTO updateRack(Long id, RackInputDTO input){
        Rack rack = rackRepo.getById(id);
        if (input.serialNumber() != null) {
            rack.setSerialNumber(input.serialNumber());
        }
        if(input.status()!=null) {
            rack.setStatus(input.status());
        }
        if(input.teamId() != null){
            rack.setTeamID(input.teamId());
        }
        if(input.defaultLocation() != null) {
            rack.setDefaultLocation(input.defaultLocation());
        }
        rackRepo.persistAndFlush(rack);
        return rack.toDTO();
    }

    @Transactional
    public void deleteRackById(Long rackId){
        rackRepo.deleteById(rackId);
    }

    @Transactional
    public RackReturnDTO createRack(RackInputDTO input){
        Rack newRack = new Rack();
        newRack.setSerialNumber(input.serialNumber());
        newRack.setStatus(input.status());
        newRack.setTeamID(input.teamId());
        newRack.setDefaultLocation(input.defaultLocation());
        rackRepo.persistAndFlush(newRack);
        return newRack.toDTO();
    }

    public BookingReturnDTO[] getBookings(long id) {
        return rackRepo.getBookings(id);
    }

    public RackAssetReturnDTO[] getRackAssets(long id) {
        return rackRepo.getRackAssets(id);
    }
}
