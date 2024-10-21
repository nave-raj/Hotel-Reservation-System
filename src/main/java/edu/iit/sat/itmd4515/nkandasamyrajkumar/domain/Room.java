/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.domain;

import edu.iit.sat.itmd4515.nkandasamyrajkumar.web.customValidator.ValidCostPerNight;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * This class represents room entity
 * 
 * @author naveenarajkumar
 */
@Entity
@Table(name = "ROOM")
@NamedQuery(name = "Room.findAll", query = "Select r from Room r")
public class Room extends AbstractEntity {

    @NotEmpty
    @Size(min = 0, max = 4)
    @Column(name = "ROOM_NUMBER", nullable = false, unique = true)
    private String roomNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "ROOM_TYPE", nullable = false)
    private RoomType roomType;

    /* custom validator to enter a value between 1 and 1000 */
    @ValidCostPerNight
    @NotNull
    @Positive
    @Column(name = "COST_PER_NIGHT", nullable = false)
    private Double costPerNight;

    @NotNull
    @Positive
    @Column(name = "FLOOR_NUMBER", nullable = false)
    private Integer Floor;

    @Positive
    @Column(name = "MAXIMUM_OCCUPANCY")
    private Integer maximumOccupancy;

    @PastOrPresent
    @Column(name = "LAST_MAINTENANCE_DATE")
    private LocalDate lastMaintenanceDate;

    /**
     * M:M bi-directional relationship between room and Guest Room is the
     * non-owning side of the relationship (inverse) inverse is indicated by the
     * mappedBy property, which points to name of the field in the owning entity
     */
    @ManyToMany(mappedBy = "rooms")
    private List<Guest> guests = new ArrayList<>();

    /**
     *
     * @param roomNumber
     * @param roomType
     * @param costPerNight
     * @param Floor
     * @param maximumOccupancy
     * @param lastMaintenanceDate
     */
    public Room(String roomNumber, RoomType roomType, Double costPerNight, Integer Floor, Integer maximumOccupancy, LocalDate lastMaintenanceDate) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.costPerNight = costPerNight;
        this.Floor = Floor;
        this.maximumOccupancy = maximumOccupancy;
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    /**
     *
     */
    public Room() {
    }

    /**
     *
     * @return
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     *
     * @param roomNumber
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     *
     * @return
     */
    public RoomType getRoomType() {
        return roomType;
    }

    /**
     *
     * @param roomType
     */
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    /**
     *
     * @return
     */
    public Double getCostPerNight() {
        return costPerNight;
    }

    /**
     *
     * @param costPerNight
     */
    public void setCostPerNight(Double costPerNight) {
        this.costPerNight = costPerNight;
    }

    /**
     *
     * @return
     */
    public Integer getFloor() {
        return Floor;
    }

    /**
     *
     * @param Floor
     */
    public void setFloor(Integer Floor) {
        this.Floor = Floor;
    }

    /**
     *
     * @return
     */
    public Integer getMaximumOccupancy() {
        return maximumOccupancy;
    }

    /**
     *
     * @param maximumOccupancy
     */
    public void setMaximumOccupancy(Integer maximumOccupancy) {
        this.maximumOccupancy = maximumOccupancy;
    }

    /**
     *
     * @return
     */
    public LocalDate getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    /**
     *
     * @param lastMaintenanceDate
     */
    public void setLastMaintenanceDate(LocalDate lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    /**
     *
     * @return
     */
    public List<Guest> getGuests() {
        return guests;
    }

    /**
     *
     * @param Guests
     */
    public void setGuests(List<Guest> Guests) {
        this.guests = Guests;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
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
        final Room other = (Room) obj;

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
        return "Room{" + "id="+ id + ", roomNumber=" + roomNumber + ", roomType=" + roomType + ", costPerNight=" + costPerNight + ", Floor=" + Floor + ", maximumOccupancy=" + maximumOccupancy + ", lastMaintenanceDate=" + lastMaintenanceDate + '}';
    }

}
