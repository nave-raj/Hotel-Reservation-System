/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author naveenarajkumar
 */
public class AbstractJPATest {

    private static EntityManagerFactory emf;

    /**
     *
     */
    protected EntityManager em;

    /**
     *
     */
    protected EntityTransaction et;

    /**
     *
     */
    @BeforeAll
    public static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("itmd4515testPU");
    }

    /**
     *
     */
    @BeforeEach
    public void beforeEach() {
        em = emf.createEntityManager();
        et = em.getTransaction();

        /* creating a test data */
        Room room1 = new Room("A1", RoomType.SINGLE, 100.34, 1, 1, LocalDate.of(2023, 12, 9));
        et.begin();
        em.persist(room1);
        et.commit();
    }
    
    /**
     *
     */
    @AfterEach
    public void afterEach() {
        /* cleaning up the test data created */
        Room r = em.createQuery("select r from Room r where r.roomNumber='A1'", Room.class).getSingleResult();
        
        /* deleting the data from database */
        et.begin();
        em.remove(r);
        et.commit();
        
        em.close();
    }
    
    /**
     *
     */
    @AfterAll
    public static void afterAll() {
        emf.close();
    }
}
