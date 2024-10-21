/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.domain;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author naveenarajkumar
 */
public class JPARelationshipTest extends AbstractJPATest {

    /**
     * Validating 1:1 uni-directional relationship
     */
    @Test
    public void uniDirectionalRelationshipTest() {
        Hotel hotel = new Hotel("Marriot");
        Staff staff = new Staff("Claire", "claire@gmail.com", LocalDate.of(2019, 5, 12));
        staff.setHotel(hotel);

        et.begin();
        //persisting the entity that doesn't own the relation
        em.persist(hotel);
        // persisting the owning entity(staff) after the non owning one
        em.persist(staff);
        et.commit();

        /* reading the data from database and asserting the data for validation */
        Staff staffDataFromDatabase = em.find(Staff.class, staff.getId());
        assertNotNull(staffDataFromDatabase.getHotel());
        assertEquals("Marriot", staffDataFromDatabase.getHotel().getName());
    }

    /**
     * Validating M:M bi-directional relationship
     */
    @Test
    public void biDirectionalRelationshipTest() {
        Room room = new Room("A6", RoomType.SUITE, Double.valueOf(389), 6, 5, LocalDate.now());
        Guest guest = new Guest("Lucy", "lucy@gmail.com", LocalDate.of(2000, 6, 5));

        /*
          case 1:
          creating and persisting the object instances but not managing
          the relationships on both sides.
         */
        /*
          case 2:
          managing the non-owning side but not setting the owning side
          result : no data inserted in the join table even after setting the data in
          non-owning side.
         */
        /*
          case 3:
          setting the owning side and not the inverse side
          result: the joining table has the data. However, we have data in the owning
          collection but not the inverse collection.
         */
        /*
          case 4:
          setting the data in both owning and non-owning side.
          result: the joining table has data and both the collections has data
         */
        guest.addRoom(room);

        et.begin();
        em.persist(room);
        em.persist(guest);
        et.commit();

        /* validating the data in ownong and non-owning side */
        assertFalse(guest.getRooms().isEmpty());
        assertEquals(1,room.getGuests().size());
        assertTrue(guest.getRooms().size() == 1);

        /* cleaning the test data */
        et.begin();
        // unsetting the relationships
        guest.removeRoom(room);
        em.remove(room);
        em.remove(guest);
        et.commit();

    }
}
