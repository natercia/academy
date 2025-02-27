package com.ctw.workstation.team.entity;

import com.ctw.workstation.repositories.TeamRepository;
import com.ctw.workstation.team.config.ctwAcademyResource;
import com.ctw.workstation.team.config.ctwAcademyTestProfile;
import io.quarkus.test.InjectMock;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestProfile(ctwAcademyTestProfile.class)
class TeamServiceTest {

    //Ao fazer inject, todas as dependencias são injetadas também
    @Inject
    TeamService teamService;

    //@InjectMock //inject mock é do quarkus, inject mocks é do mockito
    //TeamRepository teamRepository;

    @Inject
    TeamRepository teamRepository;

    @Test
    @DisplayName("Create Team")
    void create_team(){

        //TeamRepository teamRepository1 = Mockito.mock(TeamRepository.class);
        //QuarkusMock.installMockForType(teamRepository, TeamRepository.class);
        //QuarkusMock.installMockForInstance(teamRepository1, TeamRepository.class);

        //teamRepository.persist(Mockito.any(Team.class));

        //Mockito.when(teamRepository.persist(Mockito.any(Team.class))).thenThrow((new IllegalStateException("Nao")));
        //Mockito.doThrow(new IllegalArgumentException("NOOOOOOOOOOOOOOOOOOOOOOOOOOOO"))
        //        .when(teamRepository)
        //        .persistAndFlush(Mockito.any(Team.class));


        TeamInputDTO tiDTO = new TeamInputDTO("Maria", "Car", "Lisbon");

        TeamReturnDTO toDTO = teamService.createTeam(tiDTO);

        assertThat(toDTO)
                .as("Persisted DTO has no null values")
                .hasNoNullFieldsOrProperties()
                .usingRecursiveComparison()
                .ignoringFields("id", "modifiedAt", "createdAt")
                .as("Dto was returned with same values as input provided for %s", tiDTO.name())
                .isEqualTo(tiDTO);

        assertThat(teamRepository.findById(toDTO.id())).isNotNull();

        //assertThat(teamRepository.findByIdOptional(toDTO.id())).isPresent();


    }

}