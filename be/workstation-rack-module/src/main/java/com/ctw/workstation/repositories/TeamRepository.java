package com.ctw.workstation.repositories;

import com.ctw.workstation.rack.entity.RackReturnDTO;
import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.teammember.entity.TeamMemberReturnDTO;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;

import java.util.Collection;


@ApplicationScoped
public class TeamRepository implements PanacheRepository<Team> {
    public Team findTeamByName(String teamName) {
        return find("name", teamName).firstResult();
    }

    public Collection<Team> getAll(){
        return findAll().stream().toList();
    }

    public Team getById(Long teamId) {
        return find("id", teamId).firstResult();
    }

    public TeamMemberReturnDTO[] getTeamMembers(Long teamId){
        Team t = getById(teamId);
        return t.getTeamMembers();
    }

    public RackReturnDTO[] getRacks(long teamId) {
        Team t = getById(teamId);
        return t.getRacks();
    }
}
