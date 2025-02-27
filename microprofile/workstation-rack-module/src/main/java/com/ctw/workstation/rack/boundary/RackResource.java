package com.ctw.workstation.rack.boundary;

import com.ctw.workstation.rack.entity.RackReturnDTO;
import com.ctw.workstation.rack.entity.RackInputDTO;

import com.ctw.workstation.rack.entity.RackService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
//import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("/workstation/racks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RackResource {

    @Inject
    RackService service;


    @GET
    public Response getRacks() {
        return Response
                .status(Response.Status.OK)
                .entity(service.getAllRacks())
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getRackByID(@PathParam("id") long id) {
        return Response
                .status(Response.Status.OK)
                .entity(service.getRackById(id))
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

    @GET
    @Path("/{id}/rackassets")
    public Response getRackAssets(@PathParam("id") long id) {
        return Response
                .status(Response.Status.OK)
                .entity(service.getRackAssets(id))
                .build();
    }

    @GET
    @Path("/racks")
    public Response getRackByStatus(@QueryParam("status") String status) {
        return Response
                .status(Response.Status.OK)
                .entity(service.getRacksByStatus(status))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response updateRackByID(@PathParam("id") Long id,  RackInputDTO input) {
        return Response
                .status(Response.Status.OK)
                .entity(service.updateRack(id, input))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRackByID(@PathParam("id") Long id) {
        service.deleteRackById(id);
        return Response
                .status(Response.Status.OK)
                .build();
    }

    @POST
    public Response addRack(RackInputDTO input) {
        return Response
                .status(Response.Status.CREATED)
                .entity(service.createRack(input))
                .build();

    }
}
