/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @author naveenarajkumar
 */
public class RoomValidationTest {

    private static Validator validator;

    /**
     *
     */
    @BeforeAll
    public static void beforeAll() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     *
     */
    @BeforeEach
    public void beforeEach() {
    }

    /**
     *
     */
    @Test
    @DisplayName("Pass: Hotel Room Number is valid")
    public void validHotelRoomNumberSize() {
        Room room = new Room("A6", RoomType.SUITE, Double.valueOf(389), 6, 5, LocalDate.now());
        Set<ConstraintViolation<Room>> violations = validator.validate(room);
        assertEquals(0, violations.size());
    }

    /**
     *
     */
    @Test
    @DisplayName("Fail: Hotel Room Number is Not Valid")
    public void invalidHotelRoomNumberSize() {
        Room room = new Room("FA1234", RoomType.SUITE, Double.valueOf(389), 6, 5, LocalDate.now());
        Set<ConstraintViolation<Room>> violations = validator.validate(room);
        assertEquals(1, violations.size());
    }

    /**
     *
     */
    @Test
    @DisplayName("Pass: Cost per night is Valid")
    public void validCostPerNight() {
        Room room = new Room("F31", RoomType.SINGLE, Double.valueOf(100), 6, 2, LocalDate.now());
        Set<ConstraintViolation<Room>> violations = validator.validate(room);
        assertEquals(0, violations.size());
    }

    /**
     *
     */
    @Test
    @DisplayName("Fail: Cost per night is Not Valid")
    public void invalidCostPerNight() {
        Room room = new Room("F31", RoomType.SINGLE, Double.valueOf(-100), 6, 2, LocalDate.now());
        Set<ConstraintViolation<Room>> violations = validator.validate(room);
        assertEquals(2, violations.size());
    }

    /**
     *
     */
    @Test
    @DisplayName("Pass: Floor Number is Valid")
    public void validFloorNumber() {
        Room room = new Room("F31", RoomType.SINGLE, Double.valueOf(100), 6, 2, LocalDate.now());
        Set<ConstraintViolation<Room>> violations = validator.validate(room);
        assertEquals(0, violations.size());
    }

    /**
     *
     */
    @Test
    @DisplayName("Fail: Floor Number is Not Valid")
    public void invalidFloorNumber() {
        Room room = new Room("F31", RoomType.SINGLE, Double.valueOf(100), 0, 2, LocalDate.now());
        Set<ConstraintViolation<Room>> violations = validator.validate(room);
        assertEquals(1, violations.size());
    }

    /**
     *
     */
    @Test
    @DisplayName("Fail: Room Type is Not Valid")
    public void invalidRoomType() {
        Room room = new Room("F31", null, Double.valueOf(100), 6, 2, LocalDate.now());
        Set<ConstraintViolation<Room>> violations = validator.validate(room);
        assertEquals(1, violations.size());
    }

    /**
     *
     */
    @Test
    @DisplayName("Pass: Maximum Occupancy is Valid")
    public void validOccupancy() {
        Room room = new Room("F31", RoomType.SINGLE, Double.valueOf(100), 6, 1, LocalDate.now());
        Set<ConstraintViolation<Room>> violations = validator.validate(room);
        assertEquals(0, violations.size());
    }

    /**
     *
     */
    @Test
    @DisplayName("Fail: Maximum Occupancy is Not Valid")
    public void invalidOccupancy() {
        Room room = new Room("F31", RoomType.SINGLE, Double.valueOf(100), 0, null, LocalDate.now());
        Set<ConstraintViolation<Room>> violations = validator.validate(room);
        assertEquals(1, violations.size());
    }

    /**
     *
     */
    @Test
    @DisplayName("Pass: Last Maintenance Date is Valid")
    public void validLastMaintenanceDate() {
        Room room = new Room("F31", RoomType.SINGLE, Double.valueOf(100), 6, 2, LocalDate.of(2024, Month.JANUARY, 30));
        Set<ConstraintViolation<Room>> violations = validator.validate(room);
        assertEquals(0, violations.size());
    }

    /**
     *
     */
    @Test
    @DisplayName("Fail: Last Maintenance Date is Not Valid")
    public void invalidLastMaintenanceDate() {
        Room room = new Room("F31", RoomType.SINGLE, Double.valueOf(100), 6, 2, LocalDate.of(2024, Month.JULY, 10));
        Set<ConstraintViolation<Room>> violations = validator.validate(room);
        assertEquals(1, violations.size());
    }

    /**
     *
     */
    @AfterEach
    public void afterEach() {
    }

    /**
     *
     */
    @AfterAll
    public static void AfterAll() {
    }
}
