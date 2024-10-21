/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.web;

import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.Guest;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.Hotel;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.Reservation;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.Room;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.service.ReservationService;
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
public class GuestReservationController {

    private static final Logger LOG = Logger.getLogger(GuestReservationController.class.getName());

    /* declaring the model */
    private Reservation res;

    @Inject
    GuestWelcomeController gwc;
    
    @Inject
    StaffWelcomeController swc;

    @EJB
    ReservationService resService;

    /**
     *
     */
    public GuestReservationController() {
    }

    @PostConstruct
    private void postConstruct() {

        LOG.info("Inside GuestReservationController postConstruct!!!");
        gwc.refreshGuestModel();
        /* initializing model */
        res = new Reservation();
        /* initializing the model within the model */
        res.setGuest(new Guest());
        res.setHotel(new Hotel());
        /* initialization to "preset" relationships */
        res.setGuest(gwc.getGuest());

    }

    /* action methods */

    /**
     *
     * @param res
     * @return
     */

    public String displayViewReservationPage(Reservation res) {
        this.res = res;
        LOG.info("Inside GuestReservationController displayViewReservationPage with " + res.toString());
        return "/guest/viewReservation.xhtml";
    }

    /**
     *
     * @param res
     * @return
     */
    public String displayEditReservationPage(Reservation res) {
        this.res = res;
        LOG.info("Inside GuestReservationController displayEditApptPage with " + res.toString());
        return "/guest/editReservation.xhtml";
    }

    /**
     *
     * @param res
     * @return
     */
    public String displayDeleteReservationPage(Reservation res) {
        this.res = res;
        LOG.info("Inside GuestReservationController displayDeleteReservationPage with " + res.toString());
        return "/guest/deleteReservation.xhtml";
    }

    /**
     *
     * @param room
     * @return
     */
    public String displayScheduleReservationPage(Room room) {
        res.setRoom(room);
        LOG.info("Inside GuestReservationController displayScheduleReservationPage with " + res.toString());
        return "/guest/scheduleReservation.xhtml";
    }

    /**
     *
     * @return
     */
    public String scheduleReservation() {
        LOG.info("Inside GuestReservationController scheduleReservation with " + res.toString());

        /* schedule the reservation in the service layer */
        resService.scheduleReservation(res);

        gwc.refreshGuestModel();
        return "/guest/welcome.xhtml";
    }
    
    /**
     *
     * @return
     */
    public String editReservation() {
        LOG.info("editReservation has been invoked with model: " + this.res.toString());
        resService.editReservation(res);
        gwc.refreshGuestModel();
        return "/guest/welcome.xhtml";
    }
    

    /**
     * helper methods
     * @return
     */
    
    
    public String deleteReservation() {
        LOG.info("deleteReservation has been invoked with model: " + this.res.toString());
        resService.deleteReservation(res);
        gwc.refreshGuestModel();
        return "/guest/welcome.xhtml";
    }

    /**
     *
     * @return
     */
    public Reservation getRes() {
        return res;
    }

    /**
     *
     * @param res
     */
    public void setRes(Reservation res) {
        this.res = res;
    }

}
