/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.resources;

import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.Hotel;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.service.HotelService;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author naveenarajkumar
 */
@Path("/hotelreservation/hotels")
public class HotelResource {

    private static final Logger LOG = Logger.getLogger(HotelResource.class.getName());

    @EJB
    HotelService hotelService;

    /**
     *
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Hotel> getAllHotels() {
        return hotelService.findAll();
    }

    /**
     *
     * @param h
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Hotel CreateANewHotel(Hotel h) {
        hotelService.create(h);
        LOG.info("Creating A New Hotel with " + h.toString());
        return h;
    }
    
    /**
     *
     * @param id
     * @return
     */
    @GET
    @Path("/id/{id}")
    public Hotel getHotelById(@PathParam("id") Long id){
        return hotelService.read(id);
    }

}
