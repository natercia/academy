package com.ctw.workstation.booking.boundary;

import com.ctw.workstation.booking.entity.BookingInputDTO;
import com.ctw.workstation.booking.entity.BookingReturnDTO;
import com.ctw.workstation.booking.entity.BookingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/workstation/bookings/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookingResource {

    @Inject
    BookingService service;

    @GET
    public Response getBookings() {
        return Response
                .status(Response.Status.OK)
                .entity(service.getAllBookings())
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getBookingByID(@PathParam("id") long id) {
        return Response
                .status(Response.Status.OK)
                .entity(service.getBookingById(id))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response updateBookingByID(@PathParam("id") Long id,  BookingInputDTO input) {
        return Response
                .status(Response.Status.OK)
                .entity(service.updateBooking(id, input))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBookingByID(@PathParam("id") Long id) {
        service.deleteBookingById(id);
        return Response
                .status(Response.Status.OK)
                .build();
    }

    @POST
    public Response addBooking(BookingInputDTO input) {
        return Response
                .status(Response.Status.CREATED)
                .entity(service.createBooking(input))
                .build();

    }
}