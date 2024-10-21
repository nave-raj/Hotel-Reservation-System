/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.domain;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author naveenarajkumar
 */
public class RoomJPATest extends AbstractJPATest {
    
  

    /* method to create an entry into database */
    private void createARoomInDatabase(Room room) {
        et.begin();
        em.persist(room);
        et.commit();
    }

    /* method to delete an entry into database */
    private void deleteARoomInDatabase(Room room) {
        et.begin();
        em.remove(room);
        et.commit();
    }
    
    /**
     *
     */
    @Test
    public void createRoomTest() {

        /* creating a new room entity */
        Room room2 = new Room("B12", RoomType.SUITE, 340.78, 2, 4, LocalDate.of(2024, 02, 9));
        
        createARoomInDatabase(room2);

        /* reading the entity back from the database */
        Room roomDataFromDatabase = em.find(Room.class, room2.getId());

        /* test validation cases */
        assertNotNull(roomDataFromDatabase);
        assertEquals(room2.getId(), roomDataFromDatabase.getId());
        assertEquals(room2.getFloor(), roomDataFromDatabase.getFloor());

        /* deleting the created room data */
        deleteARoomInDatabase(roomDataFromDatabase);
        
    }
    
    /**
     *
     */
    @Test
    public void readRoomTest() {
        /* reading an already created room in the before each method */
        Room existingRoomInDatabase = em.createQuery("select r from Room r where r.roomNumber='A1'", Room.class).getSingleResult();

        /* test validation cases */
        assertNotNull(existingRoomInDatabase);
        assertTrue(existingRoomInDatabase.getCostPerNight() > 0);
        assertEquals(RoomType.SINGLE, existingRoomInDatabase.getRoomType());
        
    }
    
    /**
     *
     */
    @Test
    public void updateRoomTest() {
        /* reading an already created room in the before each method */
        Room existingRoomInDatabase = em.createQuery("select r from Room r where r.roomNumber='A1'", Room.class).getSingleResult();

        /* updating the data read in the database */
        et.begin();
        existingRoomInDatabase.setCostPerNight(76.99);
        et.commit();

        /* reading the updated data from database*/
        Room updatedRoomFromDatabase = em.createQuery("select r from Room r where r.roomNumber='A1'", Room.class).getSingleResult();

        /* test validation cases */
        assertNotNull(updatedRoomFromDatabase);
        assertEquals(Double.valueOf(76.99),updatedRoomFromDatabase.getCostPerNight());
    }
    
    /**
     *
     */
    @Test
    public void deleteRoomTest() {
        /* creating a room entry into the database */
        Room newRoom = new Room("C2", RoomType.DOUBLE, 340.78, 3, 2, LocalDate.of(2024, 01, 11));
        createARoomInDatabase(newRoom);

        /* verifying that the data is successfully created */
        Room roomDataFromDatabase = em.find(Room.class, newRoom.getId());
        assertNotNull(roomDataFromDatabase);

        /* deleting the data from database */
        deleteARoomInDatabase(roomDataFromDatabase);

        /* reading the deleted room */
        Room deletedRoom = em.find(Room.class, newRoom.getId());

        /* test validation cases */
        assertNull(deletedRoom);
        
    }

    
    
    
}
