/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.service;

import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.Guest;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.Hotel;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.Reservation;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.Room;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.RoomType;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.Staff;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.security.Group;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.security.GroupService;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.security.User;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.security.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import java.time.LocalDate;
import java.util.logging.Logger;

/**
 *
 * @author naveenarajkumar
 */
@Startup
@Singleton
public class StartupDbInitializer {

    private static final Logger LOG = Logger.getLogger(StartupDbInitializer.class.getName());

    /* entities behind these services does not own a relationship */
    @EJB
    HotelService hotelService;
    @EJB
    RoomService roomService;

    /* entities behind these services own a relationship */
    @EJB
    GuestService guestService;
    @EJB
    StaffService staffService;
    @EJB
    ReservationService reservationService;

    /* security services */
    @EJB
    UserService userService;

    @EJB
    GroupService groupService;

    /**
     *
     */
    public StartupDbInitializer() {

    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("StartupDbInitializer.postConstruct");

        /* Start of Security realm initialization */
        Group staffGroup = new Group("STAFF_GROUP", "Security realm Staff group");
        Group guestGroup = new Group("GUEST_GROUP", "Security realm Guest group");
        Group adminGroup = new Group("ADMIN_GROUP", "Security realm for administrative users");
        groupService.create(staffGroup);
        groupService.create(guestGroup);
        groupService.create(adminGroup);

        User staff1 = new User("staff1", "staff1");
        staff1.addGroup(staffGroup);
        staff1.addGroup(adminGroup);
        userService.create(staff1);

        User staff2 = new User("staff2", "staff2");
        staff2.addGroup(staffGroup);
        staff2.addGroup(guestGroup);
        userService.create(staff2);

        User staff3 = new User("staff3", "staff3");
        staff3.addGroup(staffGroup);
        userService.create(staff3);

        User guest1 = new User("guest1", "guest1");
        guest1.addGroup(guestGroup);
        userService.create(guest1);

        User guest2 = new User("guest2", "guest2");
        guest2.addGroup(guestGroup);
        userService.create(guest2);
        
        User admin = new User("admin", "admin");
        admin.addGroup(adminGroup);
        userService.create(admin);

        /* End of Security realm initialization */

        /* creating data in database for entities not having any ownership */
        Hotel h1 = new Hotel("Hotel One");
        Hotel h2 = new Hotel("Hotel Two");

        hotelService.create(h1);
        hotelService.create(h2);

        Room r1 = new Room("B12", RoomType.SUITE, 340.78, 2, 4, LocalDate.of(2024, 02, 9));
        Room r2 = new Room("C2", RoomType.DOUBLE, 340.78, 3, 2, LocalDate.of(2024, 01, 11));
        Room r3 = new Room("F31", RoomType.SINGLE, Double.valueOf(100), 6, 2, LocalDate.now());
        Room r4 = new Room("A6", RoomType.SUITE, Double.valueOf(389), 6, 5, LocalDate.now());

        roomService.create(r1);
        roomService.create(r2);
        roomService.create(r3);
        roomService.create(r4);

        /* creating data in database for entities not having ownership */
        Staff s1 = new Staff("Maria", "maria.c@gmail.com", LocalDate.of(2023, 01, 11));
        s1.setHotel(h1);
        s1.setUser(staff1);
        Staff s2 = new Staff("Lucy", "lucy09@gmail.com", LocalDate.of(2024, 01, 11));
        s2.setHotel(h1);
        s2.setUser(staff2);

        staffService.create(s1);
        staffService.create(s2);

        Guest g1 = new Guest("Ananya", "ananya@gmail.com", LocalDate.of(2000, 03, 13));
        g1.addRoom(r4);
        g1.setUser(guest1);
        Guest g2 = new Guest("Anuj", "anuj08@gmail.com", LocalDate.of(1998, 04, 13));
        g2.addRoom(r2);
        g2.setUser(guest2);
        Guest g3 = new Guest("Lucy Guest", "lucy09@gmail.com", LocalDate.of(1998, 05, 19));
        g3.addRoom(r1);
        g3.setUser(staff2);
        

        guestService.create(g1);
        guestService.create(g2);
        guestService.create(g3);

        Reservation res1 = new Reservation(LocalDate.of(2024, 06, 01), LocalDate.of(2024, 06, 04));
        res1.scheduleReservation(r2, g2, h1);
        reservationService.create(res1);

        Reservation res2 = new Reservation(LocalDate.of(2024, 07, 13), LocalDate.of(2024, 07, 14));
        res2.scheduleReservation(r1, g1, h1);
        reservationService.create(res2);

        /* loggers for verifying outputs for lab 6 */
        LOG.info("======================== Staff Entities and thier Relationships =========================");

        for (Staff s : staffService.findAll()) {
            LOG.info(s.toString());
            LOG.info("\n\t===== Uni directional 1:1 relationship with Hotel and Staff =====");
            LOG.info("\t" + s.getHotel().toString());
            LOG.info("=======================================================================================\n");

        }

        LOG.info("======================== Guest Entities and their Relationships =========================");

        for (Guest guest : guestService.findAll()) {
            LOG.info(guest.toString());
            LOG.info("\n\t===== 1:M bidirectional relationship for Guest and Reservation =====");
            for (Reservation reservation : guest.getReservations()) {
                LOG.info("\t" + reservation.toString());
            }

            LOG.info("\n\t===== M:M bidirectional relationship for Guest and Rooms =====");
            for (Room r : guest.getRooms()) {
                LOG.info("\t" + r.toString());
            }
            LOG.info("=======================================================================================");
        }

        LOG.info("======================== Reservation Entities and their Relationships =========================");
        for (Reservation res : reservationService.findAll()) {
            LOG.info(res.toString());
            LOG.info("\n\t===== 1:M unidirectional relationship for Room and Reservation =====");
            LOG.info("\t" + res.getRoom().toString());

            LOG.info("\n\t===== 1:M unidirectional relationship for Hotel and Reservations =====");
            LOG.info("\t" + res.getHotel().toString());

            LOG.info("=======================================================================================");

        }

    }
}
