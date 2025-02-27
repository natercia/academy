package com.ctw.workstation.rack.entity;

import com.ctw.workstation.BuildableObject;
import com.ctw.workstation.booking.entity.Booking;
import com.ctw.workstation.booking.entity.BookingReturnDTO;
import com.ctw.workstation.rackasset.entity.RackAsset;
import com.ctw.workstation.rackasset.entity.RackAssetReturnDTO;
import com.ctw.workstation.team.entity.Team;
import io.quarkus.security.IdentityAttribute;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "t_rack")
public class Rack{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rackIdGenerator")
    @SequenceGenerator(name = "rackIdGenerator", sequenceName = "SEQ_RACK_ID")
    private Long id;
    @Column(name = "serial_number", length = 20, nullable = false)
    private String serialNumber;
    @Column(name = "status", length = 20)
    private String status;
    @Column(name = "team_id", nullable = false)
    public Long teamId;
    @Column(name = "default_location", length = 10)
    private String defaultLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", insertable=false, updatable=false, nullable = false)
    private Team team;

    @OneToMany(mappedBy = "rackId", fetch = FetchType.LAZY)
    private List<Booking> bookings;
    @OneToMany(mappedBy = "rackId", fetch = FetchType.LAZY)
    private List<RackAsset> rackAssets;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_at")
    private LocalDateTime modified_at;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime created_at;

    public Rack() {
    }

    private Rack(RackBuilder builder) {
        this.id = builder.id;
        this.serialNumber = builder.serialNumber;
        this.status= builder.status;
        this.team = new Team();
        this.team.setId(builder.teamID);

    }

    public static class RackBuilder {
        private String serialNumber;
        private Long id;
        private String status;
        private Long teamID;

        public RackBuilder() {
        }

        public RackBuilder setSerialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }

        public RackBuilder setID(Long id) {
            this.id = id;
            return this;
        }

        public RackBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public RackBuilder setTeamID(Long teamID) {
            this.teamID = teamID;
            return this;
        }

        public Rack build() {
            return new Rack(this);
        }
    }

    public void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Long getTeamID() {
        return this.teamId;
    }

    public void setTeamID(Long teamID) {
        this.teamId = teamID;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDefaultLocation() {
        return defaultLocation;
    }

    public LocalDateTime getModified_at() {
        return modified_at;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setModifiedAt(LocalDateTime modified_at) {
        this.modified_at = modified_at;
    }

    public void setCreatedAt(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public RackReturnDTO toDTO() {
        return new RackReturnDTO(this.getId(), this.getSerialNumber(), this.getTeamID(), this.getStatus(), this.getDefaultLocation(), this.getCreated_at(), this.getModified_at());
    }

    public BookingReturnDTO[] getBookings() {
        return bookings.stream().map(Booking::toDTO).toList().toArray(new BookingReturnDTO[bookings.size()]);
    }

    public RackAssetReturnDTO[] getRackAssets() {
        return rackAssets.stream().map(RackAsset::toDTO).toList().toArray(new RackAssetReturnDTO[rackAssets.size()]);
    }
}
