package com.ctw.workstation.rackasset.entity;

import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.repositories.RackAssetRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.Collection;

@Singleton
public class RackAssetService {

    @Inject
    RackAssetRepository rackAssetRepo;

    public RackAssetReturnDTO[] getAllRackAssets(){
        Collection<RackAsset> rackAssets = rackAssetRepo.getAll();
        return rackAssets.stream().map(RackAsset::toDTO).toList().toArray(new RackAssetReturnDTO[rackAssets.size()]);
    }

    public RackAssetReturnDTO getRackAssetById(Long rackId){
        return rackAssetRepo.getById(rackId).toDTO();
    }

    @Transactional
    public RackAssetReturnDTO updateRackAsset(Long id, RackAssetInputDTO input){
        RackAsset rackAsset = rackAssetRepo.getById(id);
        if (input.asset_tag() != null) {
            rackAsset.setAsset_tag(input.asset_tag());
        }
        if(input.rackID()!=null) {
            rackAsset.setRackID(input.rackID());
        }
        rackAssetRepo.persistAndFlush(rackAsset);
        return rackAsset.toDTO();
    }

    @Transactional
    public void deleteRackAssetById(Long rackId){
        rackAssetRepo.deleteById(rackId);
    }

    @Transactional
    public RackAssetReturnDTO createRackAsset(RackAssetInputDTO input){
        RackAsset newRackAsset = new RackAsset();
        newRackAsset.setAsset_tag(input.asset_tag());
        newRackAsset.setRackID(input.rackID());
        rackAssetRepo.persistAndFlush(newRackAsset);
        return newRackAsset.toDTO();
    }
}
