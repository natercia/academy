package com.ctw.workstation.team.entity;

import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.rack.entity.RackReturnDTO;
import com.ctw.workstation.teammember.entity.TeamMember;
import com.ctw.workstation.teammember.entity.TeamMemberReturnDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "t_team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teamIdGenerator")
    @SequenceGenerator(name = "teamIdGenerator", sequenceName = "SEQ_TEAM_ID")
    private Long id;
    @Column(name = "name", length = 20, nullable = false)
    private String name;
    @Column(name = "product", length = 20, nullable = false)
    private String product;
    @Column(name = "default_location", length = 10)
    private String default_location;

    @OneToMany(mappedBy = "teamId", fetch = FetchType.LAZY)
    private List<Rack> racks;
    @OneToMany(mappedBy = "teamId", fetch = FetchType.LAZY)
    private List<TeamMember> teamMembers;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_at")
    private LocalDateTime modified_at;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime created_at;

    private Team(Team.TeamBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.product = builder.product;
    }

    public Team() {

    }

    public static class TeamBuilder {
        private String name;
        private Long id;
        private String product;

        public TeamBuilder() {
        }

        public Team.TeamBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public Team.TeamBuilder setID(Long id) {
            this.id = id;
            return this;
        }

        public Team.TeamBuilder setProduct(String product) {
            this.product = product;
            return this;
        }
        public Team build() {
            return new Team(this);
        }
    }

    public String getName() {
        return name;
    }

    public String getProduct() {
        return product;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public String getDefault_location() {
        return default_location;
    }

    public LocalDateTime getModified_at() {
        return modified_at;
    }
    public void setDefault_location(String default_location) {
        this.default_location = default_location;
    }

    public void setModifiedAt(LocalDateTime modified_at) {
        this.modified_at = modified_at;
    }

    public void setCreatedAt(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public TeamMemberReturnDTO[] getTeamMembers() {
        return teamMembers.stream().map(TeamMember::toDTO).toList().toArray(new TeamMemberReturnDTO[teamMembers.size()]);
    }
    public TeamReturnDTO toDTO(){
        return new TeamReturnDTO(this.getId(), this.getName(), this.getProduct(), this.getDefault_location(), this.getCreated_at(), this.getModified_at());
    }

    public RackReturnDTO[] getRacks() {
        return racks.stream().map(Rack::toDTO).toList().toArray(new RackReturnDTO[racks.size()]);
    }
}
