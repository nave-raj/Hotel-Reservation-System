/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.domain;

import edu.iit.sat.itmd4515.nkandasamyrajkumar.security.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents guest entity
 *
 * @author naveenarajkumar
 */
@Entity
@Table(name = "GUEST")
@NamedQuery(name = "Guest.findAll", query = "select g from Guest g")
@NamedQuery(name = "Guest.findByUsername", query = "select g from Guest g where g.user.userName = :uname")
public class Guest extends AbstractNamedEntity {

    @NotNull
    @Email
    @Column(name = "GUEST_EMAIL", nullable = false, unique = true)
    private String email;

    @Past
    @Column(name = "DATE_OF_BIRTH", nullable = false)
    private LocalDate dateOfBirth;

    /**
     * M:M bidirectional relationship between Guest and Room Guest is the owner
     * (owning side of the relationship)
     *
     * The JoinTable can be customized with annotation on the owning entity side
     *
     */
    @ManyToMany
    @JoinTable(name = "GUEST_ROOMS",
            joinColumns = @JoinColumn(name = "GUEST_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROOM_ID"))
    private List<Room> rooms = new ArrayList<>();

    /**
     * 1:M or M:1 bidirectional relationship between Guest and Reservation
     * Reservation is the owner (owning side of the relationship)
     */
    @OneToMany(mappedBy = "guest")
    private List<Reservation> reservations = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    /**
     * helper methods in business domain to manage both the sides of the
     * relationship
     *
     * @param room
     */
    public void addRoom(Room room) {
        if (!this.rooms.contains(room)) {
            this.rooms.add(room);
        }
        if (!room.getGuests().contains(this)) {
            room.getGuests().add(this);
        }
    }

    /**
     *
     * @param room
     */
    public void removeRoom(Room room) {
        if (this.rooms.contains(room)) {
            this.rooms.remove(room);
        }
        if (room.getGuests().contains(this)) {
            room.getGuests().remove(this);
        }
    }


    /* constructor with arguments for Guest */
    /**
     *
     * @param name
     * @param email
     * @param dateOfBirth
     */
    public Guest(String name, String email, LocalDate dateOfBirth) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    /* no argument Constructor */
    /**
     *
     */
    public Guest() {
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     *
     * @param dateOfBirth
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     *
     * @return
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     *
     * @param Rooms
     */
    public void setRooms(List<Room> Rooms) {
        this.rooms = Rooms;
    }

    /**
     *
     * @return
     */
    public List<Reservation> getReservations() {
        return reservations;
    }

    /**
     *
     * @param reservations
     */
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.id);
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
        final Guest other = (Guest) obj;

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
        return "Guest{" + "id=" + id + ", name=" + name + ", email=" + email + ", dateOfBirth=" + dateOfBirth + '}';
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
