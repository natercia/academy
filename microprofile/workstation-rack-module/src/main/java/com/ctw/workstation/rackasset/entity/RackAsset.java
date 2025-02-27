package com.ctw.workstation.rackasset.entity;

import com.ctw.workstation.rack.entity.Rack;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_rack_asset")
public class RackAsset {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rackAssetIdGenerator")
    @SequenceGenerator(name = "rackAssetIdGenerator", sequenceName = "SEQ_RACK_ASSET_ID")
    private Long id;
    @Column(name = "asset_tag", length = 20)
    private String asset_tag;
    @Column(name = "rack_id")
    private Long rackId;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_at")
    private LocalDateTime modified_at;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime created_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rack_id", updatable = false, insertable = false, nullable = false)
    public Rack ownerRack;

    public RackAsset() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAsset_tag() {
        return asset_tag;
    }

    public void setAsset_tag(String asset_tag) {
        this.asset_tag = asset_tag;
    }

    public Long getRackID() {
        return rackId;
    }

    public void setRackID(Long rackID) {
        this.rackId = rackID;
    }

    public LocalDateTime getModified_at() {
        return modified_at;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreatedAt(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setModifiedAt(LocalDateTime modified_at) {
        this.modified_at = modified_at;
    }

    public RackAssetReturnDTO toDTO(){
        return new RackAssetReturnDTO(this.getId(), this.getAsset_tag(), this.getRackID(), this.getCreated_at(), this.getModified_at());
    }
}
