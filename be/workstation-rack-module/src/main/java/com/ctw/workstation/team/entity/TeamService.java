package com.ctw.workstation.team.entity;

import com.ctw.workstation.rack.entity.RackReturnDTO;
import com.ctw.workstation.repositories.TeamRepository;
import com.ctw.workstation.teammember.entity.TeamMemberReturnDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.Collection;

@Singleton
public class TeamService {

    @Inject
    TeamRepository teamRepo;

    public TeamReturnDTO[] getAllTeams(){
        Collection<Team> teams = teamRepo.getAll();
        return teams.stream().map(Team::toDTO).toList().toArray(new TeamReturnDTO[teams.size()]);
    }

    public TeamReturnDTO getTeamById(Long teamId){
        return teamRepo.getById(teamId).toDTO();
    }

    @Transactional
    public TeamReturnDTO updateTeam(Long id, TeamInputDTO input){
        Team team = teamRepo.getById(id);
        if (input.name() != null) {
            team.setName(input.name());
        }
        if(input.product()!=null) {
            team.setProduct(input.product());
        }
        if(input.defaultLocation()!=null) {
            team.setDefault_location(input.defaultLocation());
        }
        teamRepo.persistAndFlush(team);
        return team.toDTO();
    }

    @Transactional
    public void deleteTeamById(Long teamId){
        teamRepo.deleteById(teamId);
    }

    @Transactional
    public TeamReturnDTO createTeam(TeamInputDTO input){
        Team newTeam = new Team();
        newTeam.setName(input.name());
        newTeam.setProduct(input.product());
        newTeam.setDefault_location(input.defaultLocation());
        teamRepo.persistAndFlush(newTeam);
        return newTeam.toDTO();
    }

    public TeamMemberReturnDTO[] getTeamMembers(Long teamId) {
        return teamRepo.getTeamMembers(teamId);
    }

    public RackReturnDTO[] getRacks(long teamId) {
        return teamRepo.getRacks(teamId);
    }
}
