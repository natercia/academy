package com.ctw.workstation.teammember.entity;

import com.ctw.workstation.booking.entity.BookingReturnDTO;
import com.ctw.workstation.repositories.TeamMemberRepository;
import com.ctw.workstation.team.entity.Team;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.Collection;

@Singleton
public class TeamMemberService {

    @Inject
    TeamMemberRepository teamMemberRepo;

    public TeamMemberReturnDTO[] getAllTeamMembers(){
        Collection<TeamMember> teamMembers = teamMemberRepo.getAll();
        return teamMembers.stream().map(TeamMember::toDTO).toList().toArray(new TeamMemberReturnDTO[teamMembers.size()]);
    }

    public TeamMemberReturnDTO getTeamMemberById(Long teamMemberId){
        return teamMemberRepo.getById(teamMemberId).toDTO();
    }

    @Transactional
    public TeamMemberReturnDTO updateTeamMember(Long id, TeamMemberInputDTO input){
        TeamMember teamMember = teamMemberRepo.getById(id);
        if (input.ctwID() != null) {
            teamMember.setCtwID(input.ctwID());
        }
        if(input.name()!=null) {
            teamMember.setName(input.name());
        }
        if(input.teamID() != null){
            teamMember.setTeamID(input.teamID());
        }
        teamMemberRepo.persistAndFlush(teamMember);
        return teamMember.toDTO();
    }

    @Transactional
    public void deleteTeamMemberById(Long teamMemberId){
        teamMemberRepo.deleteById(teamMemberId);
    }

    @Transactional
    public TeamMemberReturnDTO createTeamMember(TeamMemberInputDTO input){
        TeamMember newTeamMember = new TeamMember();
        newTeamMember.setName(input.name());
        newTeamMember.setCtwID(input.ctwID());
        newTeamMember.setTeamID(input.teamID());
        teamMemberRepo.persistAndFlush(newTeamMember);
        return newTeamMember.toDTO();
    }

    public BookingReturnDTO[] getBookings(long id) {
        return teamMemberRepo.getRacks(id);
    }
}
