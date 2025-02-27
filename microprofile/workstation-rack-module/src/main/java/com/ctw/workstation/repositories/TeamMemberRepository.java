package com.ctw.workstation.repositories;

import com.ctw.workstation.booking.entity.BookingReturnDTO;
import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.teammember.entity.TeamMember;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;

@ApplicationScoped
public class TeamMemberRepository implements PanacheRepository<TeamMember> {

    public Collection<TeamMember> getAll(){
        return findAll().stream().toList();
    }

    public TeamMember getById(Long id){
        return find("id", id).firstResult();
    }

    public BookingReturnDTO[] getRacks(long id) {
        TeamMember tm = getById(id);
        return tm.getBookings();
    }
}
