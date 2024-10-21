/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.service;

import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.Staff;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import java.util.List;

/**
 *
 * @author naveenarajkumar
 */

@Named
@Stateless
public class StaffService extends AbstractService<Staff> {
    
    /**
     *
     */
    public StaffService () {
        super(Staff.class);
    }
    
    /**
     *
     * @return
     */
    public List<Staff> findAll(){
        return super.findAll("Staff.findAll");
    }
    
    /**
     *
     * @param username
     * @return
     */
    public Staff findByUsername(String username) {
        return em.createNamedQuery("Staff.findByUsername", Staff.class).setParameter("uname", username).getSingleResult();
    }
    
}
