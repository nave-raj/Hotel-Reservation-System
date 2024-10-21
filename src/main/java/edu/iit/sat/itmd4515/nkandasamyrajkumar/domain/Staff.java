/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.domain;

import edu.iit.sat.itmd4515.nkandasamyrajkumar.security.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * This class represents staff entity
 * 
 * @author naveenarajkumar
 */
@Entity
@Table(name = "STAFF")
@NamedQuery(name = "Staff.findAll", query = "select s from Staff s")
@NamedQuery(name = "Staff.findByUsername", query="select s from Staff s where s.user.userName = :uname")
public class Staff extends AbstractNamedEntity {

    @NotNull
    @Email
    @Column(name = "STAFF_EMAIL", nullable = false, unique = true)
    private String email;

    @PastOrPresent
    @Column(name = "DATE_OF_JOINING", nullable = false)
    private LocalDate dateOfJoining;

    /**
     * 1:1 uni-directional relationship between Staff and a Hotel Staff is the
     * owner
     */
    @OneToOne
    @JoinColumn(name = "HOTEL_ID")
    private Hotel hotel;

    
    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    /* Constructor with arguments for Staff */

    /**
     *
     * @param name
     * @param email
     * @param dateOfJoining
     */

    public Staff(String name, String email, LocalDate dateOfJoining) {
        this.name = name;
        this.email = email;
        this.dateOfJoining = dateOfJoining;
    }

    /* No Argument Constructor */

    /**
     *
     */

    public Staff() {
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
    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    /**
     *
     * @param dateOfJoining
     */
    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Staff{" + "id=" + id + ", name=" + name + ", email=" + email + ", dateOfJoining=" + dateOfJoining + '}';
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final Staff other = (Staff) obj;

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
