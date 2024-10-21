/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

/**
 * This class represents reservation entity for room booking
 * 
 * @author naveenarajkumar
 */
@Entity
@Table(name = "RESERVATION")
@NamedQuery(name = "Reservation.findAll", query = "select res from Reservation res")
public class Reservation extends AbstractEntity {

    @NotNull
    @FutureOrPresent
    @Column(name = "CHECK_IN_DATE", nullable = false)
    private LocalDate checkInDate;

    @NotNull
    @FutureOrPresent
    @Column(name = "CHECK_OUT_DATE", nullable = false)
    private LocalDate checkOutDate;

    /**
     * M:1 unidirectional relationship between Reservation and Room Reservation
     * is the owning side (Since its ManyToOne)
     */
    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    /**
     * M:1 or 1:M bidirectional relationship for Reservation and Guest
     * Reservation is the owning side (Since its ManyToOne)
     */
    @ManyToOne
    @JoinColumn(name = "GUEST_ID")
    private Guest guest;

    /**
     * M:1 unidirectional relationship between Reservation and Hotel Reservation
     * is the owning side (Since its ManyToOne)
     */
    @ManyToOne
    @JoinColumn(name = "HOTEL_ID")
    private Hotel hotel;

    /* Constructor with arguments for Reservation */

    /**
     *
     * @param checkInDate
     * @param checkOutDate
     */

    public Reservation(LocalDate checkInDate, LocalDate checkOutDate) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    /* No argument constructor */

    /**
     *
     */

    public Reservation() {
    }

    /* helper method to manage JPA relationships */

    /**
     *
     * @param r
     * @param g
     * @param h
     */

    public void scheduleReservation(Room r, Guest g, Hotel h) {
        this.room = r;
        this.guest = g;
        this.hotel = h;

        /* handling bi-directional relationships */
        if (!g.getReservations().contains(this)) {
            g.getReservations().add(this);
        }

    }

    /**
     *
     */
    public void cancelReservation() {
        if (this.guest.getReservations().contains(this)) {
            this.guest.getReservations().remove(this);
        }

        /* handling the one side */
        this.guest = null;
        this.room = null;
        this.hotel = null;
    }

    /**
     *
     * @return
     */
    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    /**
     *
     * @param checkInDate
     */
    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    /**
     *
     * @return
     */
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    /**
     *
     * @param checkOutDate
     */
    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
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

    /**
     *
     * @return
     */
    public Room getRoom() {
        return room;
    }

    /**
     *
     * @param room
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     *
     * @return
     */
    public Hotel getHotel() {
        return hotel;
    }

    /**
     *
     * @param hotel
     */
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reservation other = (Reservation) obj;

        /* returning false as we rely on generatated value while creating the database entry*/
        if (this.id == null || other.id == null) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate + ", room=" + room + ", hotel=" + hotel + ", guest=" + guest + '}';
    }

}
