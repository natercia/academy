package com.ctw.workstation.teammember.entity;

import com.ctw.workstation.booking.entity.Booking;
import com.ctw.workstation.booking.entity.BookingReturnDTO;
import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.rack.entity.RackReturnDTO;
import com.ctw.workstation.team.entity.Team;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "t_team_member")
public class TeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teamMemberIdGenerator")
    @SequenceGenerator(name = "teamMemberIdGenerator", sequenceName = "SEQ_TEAM_MEMBER_ID")
    private Long id;

    @Column(name = "ctw_id", length = 8, nullable = false)
    private String ctwID;
    @Column(name = "name", length = 20, nullable = false)
    private String name;
    @Column(name = "team_id", nullable = false)
    public Long teamId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", insertable=false, updatable=false, nullable = false)
    private Team team;

    @OneToMany(mappedBy = "requesterId", fetch = FetchType.LAZY)
    private List<Booking> bookings;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_at")
    private LocalDateTime modified_at;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime created_at;

    public TeamMember() {
    }

    public void setTeamID(Long teamID) {
        this.teamId = teamID;
    }

    public String getCtwID() {
        return ctwID;
    }

    public void setCtwID(String ctwID) {
        this.ctwID = ctwID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getTeamID() {
        return this.teamId;
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

    public TeamMemberReturnDTO toDTO() {
        return new TeamMemberReturnDTO(this.getId(), this.getTeamID(), this.getCtwID(), this.getName(), this.getModified_at(), this.getCreated_at());
    }

    public BookingReturnDTO[] getBookings() {
        return bookings.stream().map(Booking::toDTO).toList().toArray(new BookingReturnDTO[bookings.size()]);
    }
}
