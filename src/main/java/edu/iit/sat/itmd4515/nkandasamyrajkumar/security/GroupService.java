/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.security;

import edu.iit.sat.itmd4515.nkandasamyrajkumar.service.AbstractService;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 *
 * @author naveenarajkumar
 */

@Stateless
public class GroupService extends AbstractService<Group> {

    /**
     *
     */
    public GroupService() {
        super(Group.class);
    }

    /**
     *
     * @return
     */
    public List<Group> findAll() {
        return super.findAll("Group.findAll");
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Group find(String id){
        return em.find(entityClass, id);
    }

}
