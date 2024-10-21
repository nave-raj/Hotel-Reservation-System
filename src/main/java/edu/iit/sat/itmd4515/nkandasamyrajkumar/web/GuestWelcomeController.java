/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.web;

import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.Guest;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.service.GuestService;
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
public class GuestWelcomeController {

    private static final Logger LOG = Logger.getLogger(GuestWelcomeController.class.getName());

    @EJB
    GuestService guestService;
    @Inject
    LoginController loginController;

    /* model */
    private Guest guest;

    /**
     *
     */
    public GuestWelcomeController() {
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("GuestWelcomeController.postConstruct");
        /* initializing the guest model with the currently authenticated user */
        guest = guestService.findByUsername(loginController.getAuthenticatedUser());
        LOG.info("GuestWelcomeController.postConstruct: " + guest.toString());
    }
    
    /* helper methods */

    /**
     *
     */

    public void refreshGuestModel(){
        guest = guestService.findByUsername(loginController.getAuthenticatedUser());
    }

    /**
     *
     * @return
     */
    public Guest getGuest() {
        return guest;
    }

    /**
     *
     * @param guest
     */
    public void setGuest(Guest guest) {
        this.guest = guest;
    }

}
