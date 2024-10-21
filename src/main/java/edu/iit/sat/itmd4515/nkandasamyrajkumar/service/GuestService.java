/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.service;

import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.Guest;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.Room;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import java.util.List;

/**
 *
 * @author naveenarajkumar
 */
@Named
@Stateless
public class GuestService extends AbstractService<Guest> {

    /**
     *
     */
    public GuestService() {
        super(Guest.class);
    }

    /**
     *
     * @return
     */
    public List<Guest> findAll() {
        return super.findAll("Guest.findAll");
    }

    /**
     *
     * @param username
     * @return
     */
    public Guest findByUsername(String username) {
        return em.createNamedQuery("Guest.findByUsername", Guest.class).setParameter("uname", username).getSingleResult();
    }

    /**
     *
     * @param guest
     * @param room
     */
    public void createRoomForGuest(Guest guest, Room room) {
        Guest gRef= em.getReference(Guest.class, guest.getId());
        gRef.addRoom(room);
        em.merge(gRef);
    }

}
