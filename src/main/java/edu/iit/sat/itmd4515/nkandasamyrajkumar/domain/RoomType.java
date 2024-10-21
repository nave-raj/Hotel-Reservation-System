/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.domain;

/**
 * This class represents type for room entity
 * @author naveenarajkumar
 */
public enum RoomType {

    /**
     *
     */
    SINGLE("Single"),

    /**
     *
     */
    DOUBLE("Double"),

    /**
     *
     */
    SUITE("Suite");
    
    private String label;
    
    private RoomType(String label){
        this.label = label;
    }
    
    /**
     *
     * @return
     */
    public String getLabel(){
        return label;
    }
}
