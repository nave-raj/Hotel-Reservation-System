/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * This class represents hotel entity
 * 
 * @author naveenarajkumar
 */
@Entity
@XmlRootElement
@Table(name = "HOTEL")
@NamedQuery(name = "Hotel.findAll", query = "select h from Hotel h")
public class Hotel extends AbstractNamedEntity {

    /* Constructor with argument for Hotel */

    /**
     *
     * @param name
     */

    public Hotel(String name) {
        this.name = name;
    }

    /* No argument Constructor */

    /**
     *
     */

    public Hotel() {
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Hotel{" + "id=" + id + ", hotelName=" + name + '}';
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final Hotel other = (Hotel) obj;
        
        /* returning false as we rely on generatated value while creating the database entry*/
        if (this.id == null || other.id == null) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }
    
    

}
