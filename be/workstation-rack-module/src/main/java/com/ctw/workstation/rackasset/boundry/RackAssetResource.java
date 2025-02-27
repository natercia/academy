package com.ctw.workstation.rackasset.boundry;

import com.ctw.workstation.rackasset.entity.RackAssetInputDTO;
import com.ctw.workstation.rackasset.entity.RackAssetReturnDTO;
import com.ctw.workstation.rackasset.entity.RackAssetService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/workstation/rackAssets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RackAssetResource {

    @Inject
    RackAssetService service;

    @GET
    public Response getRackAssets() {
        return Response
                .status(Response.Status.OK)
                .entity(service.getAllRackAssets())
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getRackAssetByID(@PathParam("id") long id) {
        return Response
                .status(Response.Status.OK)
                .entity(service.getRackAssetById(id))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response updateRackAssetByID(@PathParam("id") Long id,  RackAssetInputDTO input) {
        return Response
                .status(Response.Status.OK)
                .entity(service.updateRackAsset(id, input))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRackAssetByID(@PathParam("id") Long id) {
        service.deleteRackAssetById(id);
        return Response
                .status(Response.Status.OK)
                .build();
    }

    @POST
    public Response addRackAsset(RackAssetInputDTO input) {
        return Response
                .status(Response.Status.CREATED)
                .entity(service.createRackAsset(input))
                .build();

    }
}
