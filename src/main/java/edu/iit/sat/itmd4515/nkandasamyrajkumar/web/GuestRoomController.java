/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.web;

import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.Room;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.RoomType;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.service.GuestService;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.service.RoomService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.logging.Logger;

/**
 *
 * @author naveenarajkumar
 */
@Named
@RequestScoped
public class GuestRoomController {
    
    private static final Logger LOG = Logger.getLogger(GuestRoomController.class.getName());
    
    @EJB
    RoomService roomService;
    @EJB
    GuestService guestService;
    @Inject
    GuestWelcomeController gwc;

    /* Model part of the MVC Type */
    private Room room;
    
    /**
     *
     */
    public GuestRoomController() {

    }
    
    @PostConstruct
    private void postConstruct() {
        /* instantiating the model object here */
        room = new Room();
        
        LOG.info("RoomController.postConstruct");
        
    }

    /* helper methods */

    /**
     *
     * @return
     */

    public RoomType[] getAllRoomTypesForForm() {
        return RoomType.values();
    }

    /* action methods */
    /**
     * MVC style JSF navigation method for viewing the booked room in read only
     * mode
     *
     * @return
     */
   
    
    public String bookRoom() {
        LOG.info("Application has been invoked with model: " + this.room.toString());
        /*roomService.create(room);*/
        guestService.createRoomForGuest(gwc.getGuest(), room);
        roomService.addGuest(gwc.getGuest(), room);
        LOG.info("SaveRoom after calling service " + this.room.toString());
        gwc.refreshGuestModel();
        return "welcome.xhtml";
    }

    /* getters and Setters */

    /**
     *
     * @return
     */

    public Room getRoom() {
        return room;
    }
    
    /**
     *
     * @param room
     */
    public void setRoom(Room room) {
        this.room = room;
    }
    
}
