package edu.iit.sat.itmd4515.nkandasamyrajkumar.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("/hotelreservation")
public class HotelReservationBaseResource {
    
    /**
     *
     * @return
     */
    @GET
    public Response ping(){
        return Response
                .ok("ping Jakarta EE")
                .build();
    }
    
    /**
     *
     * @return
     */
    @GET
    @Path("/version")
    public Response version(){
        return Response
                .ok("v1")
                .build();
    }
}
