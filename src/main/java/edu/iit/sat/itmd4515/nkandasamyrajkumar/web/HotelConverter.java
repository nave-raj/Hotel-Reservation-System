/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.web;

import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.Hotel;
import edu.iit.sat.itmd4515.nkandasamyrajkumar.service.HotelService;
import jakarta.ejb.EJB;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author naveenarajkumar
 */

@FacesConverter(value = "hotelConverter", managed = true)
public class HotelConverter implements Converter<Hotel> {
    @EJB
    HotelService hotelService;

    /**
     *
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public Hotel getAsObject(FacesContext context, UIComponent component, String value) {
        return hotelService.read(Long.valueOf(value));
    }

    /**
     *
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Hotel value) {
        return String.valueOf(value.getId());
    }
}
