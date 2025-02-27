package com.ctw.workstation.teammember.boundary;

import com.ctw.workstation.teammember.entity.TeamMemberReturnDTO;
import com.ctw.workstation.teammember.entity.TeamMemberInputDTO;
import com.ctw.workstation.teammember.entity.TeamMemberService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/workstation/teamMember")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeamMemberResource {

    @Inject
    TeamMemberService service;


    @GET
    public Response getTeamMembers() {
        return Response
                .status(Response.Status.OK)
                .entity(service.getAllTeamMembers())
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getTeamMemberByID(@PathParam("id") long id) {
        return Response
                .status(Response.Status.OK)
                .entity(service.getTeamMemberById(id))
                .build();
    }

    @GET
    @Path("/{id}/bookings")
    public Response getBookings(@PathParam("id") long id) {
        return Response
                .status(Response.Status.OK)
                .entity(service.getBookings(id))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response updateTeamMemberByID(@PathParam("id") Long id,  TeamMemberInputDTO input) {
        return Response
                .status(Response.Status.OK)
                .entity(service.updateTeamMember(id, input))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTeamMemberByID(@PathParam("id") Long id) {
        service.deleteTeamMemberById(id);
        return Response
                .status(Response.Status.OK)
                .build();
    }

    @POST
    public Response addTeamMember(TeamMemberInputDTO input) {
        return Response
                .status(Response.Status.CREATED)
                .entity(service.createTeamMember(input))
                .build();

    }
}