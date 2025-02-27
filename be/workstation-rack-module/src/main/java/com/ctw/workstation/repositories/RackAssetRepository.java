package com.ctw.workstation.repositories;

import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.rackasset.entity.RackAsset;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;

@ApplicationScoped
public class RackAssetRepository implements PanacheRepository<RackAsset> {

    public Collection<RackAsset> getAll(){
        return findAll().stream().toList();
    }

    public RackAsset getById(Long id){
        return find("id", id).firstResult();
    }
}
