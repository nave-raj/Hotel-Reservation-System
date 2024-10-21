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
public class StaffRoomController {
    
    private static final Logger LOG = Logger.getLogger(StaffRoomController.class.getName());
    
    @EJB
    RoomService roomService;
    @EJB
    GuestService guestService;
    @Inject
    GuestWelcomeController gwc;
    @Inject
    StaffWelcomeController swc;

    /* Model part of the MVC Type */
    private Room room;
    
    /**
     *
     */
    public StaffRoomController() {
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
     * @param r
     * @return
     */
    public String displayBookedRoomPage(Room r) {

        /* handling the click and assigning the model */
        this.room = r;
        LOG.info("Inside the displayBookedRoomPage with model " + r.toString());

        /* navigating to view Room JSF Page */
        return "/staff/viewRoom.xhtml";
    }
    
    /**
     *
     * @param r
     * @return
     */
    public String displayEditRoomPage(Room r) {

        /* handling the click and assigning the model */
        this.room = r;
        LOG.info("Inside the displayEditRoomPage with model " + r.toString());

        /* navigating to edit Room JSF Page */
        return "/staff/editRoom.xhtml";
    }
    
    /**
     *
     * @param r
     * @return
     */
    public String displayDeleteRoomPage(Room r) {

        /* handling the click and assigning the model */
        this.room = r;
        LOG.info("Inside displayDeleteRoomPage with model " + r.toString());

        /* navigating to delete Room JSF Page */
        return "/staff/deleteRoom.xhtml";
    }

    /**
     * 
     * action methods or composite component
     * 
     * @return
     */
    public String editRoom() {
        LOG.info("editRoom has been invoked with model: " + this.room.toString());
        roomService.editRoomForExistingGuest(room);
        swc.refreshStaffModel();
        return "/staff/welcome.xhtml";
    }
    
    /**
     *
     * @return
     */
    public String viewRoom() {
        LOG.info("viewRoom has been invoked with model: " + this.room.toString());
        return "/staff/welcome.xhtml";
    }
    
    /**
     *
     * @return
     */
    public String deleteRoom() {
        LOG.info("deleteRoom has been invoked with model: " + this.room.toString());
       
        roomService.deleteExistingRoom(room);
        swc.refreshStaffModel();
        return "/staff/welcome.xhtml";
    }
    
    /**
     *
     * @return
     */
    public String saveRoom() {
        LOG.info("Application has been invoked with model: " + this.room.toString());
        /*roomService.create(room);*/
        //guestService.createRoomForGuest(gwc.getGuest(), room);
        roomService.createRoom(room);
        LOG.info("SaveRoom after calling service " + this.room.toString());
        swc.refreshStaffModel();
        return "welcome.xhtml";
    }
    
    /**
     *
     * @return
     */
    public String bookRoom() {
        LOG.info("Application has been invoked with model: " + this.room.toString());
        /*roomService.create(room);*/
        guestService.createRoomForGuest(gwc.getGuest(), room);
        roomService.createRoom(room);
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
