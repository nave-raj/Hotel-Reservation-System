/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.web;

import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.Staff;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.security.Group;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.security.GroupService;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.security.User;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.security.UserService;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.service.StaffService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author naveenarajkumar
 */

@Named
@RequestScoped
public class StaffSignUpController {
    private static final Logger LOG = Logger.getLogger(StaffSignUpController.class.getName());
    
    /* declaring model */
    private Staff staff;
    private User user;
    
    @EJB
    StaffService staffService;
    
    @EJB
    private GroupService groupService;
    
    @EJB
    private UserService userService;
    
    /**
     *
     */
    public StaffSignUpController() {
    }
    
    /**
     *
     * @return
     */
    public String saveStaff() {
        Group group = groupService.find("STAFF_GROUP");
        user.addGroup(group);
        staff.setUser(user);
        LOG.log(Level.INFO, "New Staff Created:" +staff.toString());

        try {
            userService.create(staff.getUser());
            staffService.create(staff);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESSFULLY CREATED STAFF", "Staff created successfully!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR IN STAFF CREATION", "Failed to create Staff."));
            return "error.xhtml";
        }
        return "/admin/welcome.xhtml?faces-redirect=true";
    }
    
    @PostConstruct
    private void postConstruct(){
        LOG.info("Inside StaffSignUpController PostConstruct..!");
        staff = new Staff();
        user = new User();
    }
    
    
    /* getters and setters */

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
