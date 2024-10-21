/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.web;

import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.Guest;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.security.Group;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.security.GroupService;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.security.User;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.security.UserService;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.service.GuestService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.util.logging.Logger;

/**
 *
 * @author naveenarajkumar
 */
@Named
@RequestScoped
public class GuestSignUpController {

    private static final Logger LOG = Logger.getLogger(GuestSignUpController.class.getName());
    
    /* declaring model */
    private Guest guest;
    private User user;
    
    @EJB
    GuestService guestService;
    
    @EJB
    private GroupService groupService;
    
    @EJB
    private UserService userService;
    
    /**
     *
     */
    public GuestSignUpController() {
    }
    
    /**
     *
     * @return
     */
    public String saveGuest() {
        Group group = groupService.find("GUEST_GROUP");
        user.addGroup(group);
        guest.setUser(user);
        LOG.info("New Guest Created" + guest.toString());

        try {
            userService.create(guest.getUser());
            guestService.create(guest);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESSFULLY CREATED USER", "User created successfully!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR IN USER CREATION", "Failed to create user."));
            return "error.xhtml";
        }
        return "login.xhtml?faces-redirect=true";
    }
    
    @PostConstruct
    private void postConstruct(){
        LOG.info("Inside GuestSignUpController PostConstruct..!");
        guest = new Guest();
        user = new User();
    }
    
    
    /* getters and setters */

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

    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }
    

}
