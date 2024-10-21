/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.service;

import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.Guest;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.Hotel;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.Reservation;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.Room;
import jakarta.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author naveenarajkumar
 */
@Stateless
public class ReservationService extends AbstractService<Reservation> {

    /**
     *
     */
    public ReservationService() {
        super(Reservation.class);
    }

    /**
     *
     * @return
     */
    public List<Reservation> findAll() {
        return super.findAll("Reservation.findAll");
    }

    /**
     *
     * @param res
     */
    public void scheduleReservation(Reservation res) {
        Reservation newReservation = new Reservation(res.getCheckInDate(), res.getCheckOutDate());

        /* managing relationships with the existing helper methods */
        newReservation.scheduleReservation(
                em.getReference(Room.class, res.getRoom().getId()),
                em.getReference(Guest.class, res.getGuest().getId()),
                em.getReference(Hotel.class, res.getHotel().getId()));

        em.persist(newReservation);
    }

    /**
     *
     * @param res
     */
    public void editReservation(Reservation res) {
        /* getting the current reference of the reservation to manage */
        Reservation managedRef = em.getReference(Reservation.class, res.getId());

        /* updating the fields through the reference */
        managedRef.setCheckInDate(res.getCheckInDate());
        managedRef.setCheckOutDate(res.getCheckOutDate());
        managedRef.setHotel(res.getHotel());
        managedRef.setRoom(res.getRoom());
        managedRef.setGuest(res.getGuest());
        /* using em.merge on the managed reference */
        em.merge(managedRef);
    }

    /**
     *
     * @param res
     */
    public void deleteReservation(Reservation res) {
        /* getting the current reference to manage */
        Reservation managedRef = em.getReference(Reservation.class, res.getId());
        managedRef.cancelReservation();
        if (managedRef != null) {
            em.remove(managedRef);
        }
    }

}
