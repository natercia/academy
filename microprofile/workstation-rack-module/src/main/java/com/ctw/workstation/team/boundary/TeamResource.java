package com.ctw.workstation.team.boundary;

import com.ctw.workstation.team.entity.TeamReturnDTO;
import com.ctw.workstation.team.entity.TeamInputDTO;
import com.ctw.workstation.team.entity.TeamService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import io.quarkus.logging.Log;
import org.jboss.logging.MDC;

import java.util.UUID;

@Path("/workstation/teams/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeamResource {

    @Inject
    TeamService service;

    @GET
    public Response getTeams() {
        return Response
                .status(Response.Status.OK)
                .entity(service.getAllTeams())
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getTeamByID(@PathParam("id") long id) {
        return Response
                .status(Response.Status.OK)
                .entity(service.getTeamById(id))
                .build();
    }
    @GET
    @Path("/{id}/teammembers")
    public Response getTeamMembers(@PathParam("id") long id) {
        return Response
                .status(Response.Status.OK)
                .entity(service.getTeamMembers(id))
                .build();
    }

    @GET
    @Path("/{id}/racks")
    public Response getRacks(@PathParam("id") long id) {
        return Response
                .status(Response.Status.OK)
                .entity(service.getRacks(id))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response updateTeamByID(@PathParam("id") Long id,  TeamInputDTO input) {
        return Response
                .status(Response.Status.OK)
                .entity(service.updateTeam(id, input))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTeamByID(@PathParam("id") Long id) {
        service.deleteTeamById(id);
        return Response
                .status(Response.Status.OK)
                .build();
    }

    @POST
    public Response addTeam(TeamInputDTO input) {
        MDC.put("log", UUID.randomUUID().toString());
        Log.debugv("creating team with name {0}", input.name());
        return Response
                .status(Response.Status.CREATED)
                .entity(service.createTeam(input))
                .build();

    }
}