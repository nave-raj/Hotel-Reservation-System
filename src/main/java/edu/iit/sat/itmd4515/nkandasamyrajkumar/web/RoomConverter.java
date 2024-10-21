/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.web;

import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.Room;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.service.RoomService;
import jakarta.ejb.EJB;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author naveenarajkumar
 */
@FacesConverter(value = "roomConverter", managed = true)
public class RoomConverter implements Converter<Room> {

    @EJB
    RoomService roomService;

    /**
     *
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public Room getAsObject(FacesContext context, UIComponent component, String value) {
        return roomService.read(Long.valueOf(value));
    }

    /**
     *
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Room value) {
        return String.valueOf(value.getId());
    }

}
