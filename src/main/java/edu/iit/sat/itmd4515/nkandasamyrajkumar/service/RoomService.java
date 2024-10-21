/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.service;

import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.Guest;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.Room;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author naveenarajkumar
 */
@Named
@Stateless
public class RoomService extends AbstractService<Room> {

    /**
     *
     */
    public RoomService() {
        super(Room.class);
    }

    /**
     *
     * @return
     */
    public List<Room> findAll() {
        return super.findAll("Room.findAll");
    }

    /**
     *
     * @param username
     * @return
     */
    public Room findByUsername(String username) {
        return em.createNamedQuery("Room.findByUsername", Room.class).setParameter("uname", username).getSingleResult();
    }

    /**
     *
     * @param r
     */
    public void editRoomForExistingGuest(Room r) {

        /* getting the current reference to manage */
        Room managedRef = em.getReference(Room.class, r.getId());

        /* updating the fields through the reference */
        managedRef.setRoomNumber(r.getRoomNumber());
        managedRef.setRoomType(r.getRoomType());
        managedRef.setCostPerNight(r.getCostPerNight());
        managedRef.setFloor(r.getFloor());
        managedRef.setMaximumOccupancy(r.getMaximumOccupancy());
        managedRef.setLastMaintenanceDate(r.getLastMaintenanceDate());

        /* using em.merge on the managed reference */
        em.merge(managedRef);

    }

    /**
     *
     * @param room
     */
    public void createRoom(Room room) {
        em.persist(room);
    }

    /**
     *
     * @param r
     */
    public void deleteExistingRoom(Room r) {
        /* getting the current reference to manage */
        Room managedRef = em.getReference(Room.class, r.getId());
        List<Guest> guests = new ArrayList<Guest>(managedRef.getGuests());
        if (managedRef != null) {
            Query deleteReservationQuery = em.createQuery("DELETE FROM Reservation r WHERE r.room.id = :roomId");
            deleteReservationQuery.setParameter("roomId", r.getId());
            deleteReservationQuery.executeUpdate();

        }
        for (Guest guest : guests) {
            guest.removeRoom(managedRef);
        }
        em.remove(managedRef);

    }

    /**
     *
     * @param guest
     * @param room
     */
    public void addGuest(Guest guest, Room room) {
        Room managedRef = em.getReference(Room.class, room.getId());
        managedRef.getGuests().add(guest);
    }

}
