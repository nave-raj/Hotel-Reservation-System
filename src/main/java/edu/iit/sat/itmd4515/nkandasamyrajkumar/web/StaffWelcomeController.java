/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.web;

import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.Staff;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.service.StaffService;
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
public class StaffWelcomeController {

    private static final Logger LOG = Logger.getLogger(StaffWelcomeController.class.getName());

    @EJB
    StaffService staffService;
    @Inject
    LoginController loginController;

    /* declaring the model */
    private Staff staff;

    /**
     *
     */
    public StaffWelcomeController() {
    }

   
    @PostConstruct
    private void postConstruct() {
        LOG.info("Inside StaffWelcomeController.postConstruct");
     
        /* initializing the model with the currently authenticated user */
        staff = staffService.findByUsername(loginController.getAuthenticatedUser());
        LOG.info("StaffWelcomeController.postConstruct: " + staff.toString());
    }

   /* utility methods */

    /**
     *
     */

    public void refreshStaffModel() {
        staff = staffService.findByUsername(loginController.getAuthenticatedUser());
    }

    /**
     *
     * @return
     */
    public Staff getStaff() {
        return staff;
    }

    /**
     *
     * @param staff
     */
    public void setStaff(Staff staff) {
        this.staff = staff;
    }
    
    
}
