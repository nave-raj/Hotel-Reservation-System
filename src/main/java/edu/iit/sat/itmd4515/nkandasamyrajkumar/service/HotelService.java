/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.service;

import edu.iit.sat.itmd4515.nkandasamyrajkumar.domain.Hotel;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 * Stateless No-Interface EJB
 * 
 * @author naveenarajkumar
 */
@Named
@Stateless
public class HotelService {
    
    @PersistenceContext( name = "itmd4515PU")
    private EntityManager em;
    
    /**
     *
     */
    public HotelService(){
        
    }
    
    /**
     *
     * @param h
     */
    public void create(Hotel h){
        /* transaction begins when we invoke the method */
        em.persist(h);
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Hotel read(Long id){
        return em.find(Hotel.class, id);
    }
    
    /**
     *
     * @param h
     */
    public void update(Hotel h){
        em.merge(h);
    }
    
    /**
     *
     * @param h
     */
    public void delete(Hotel h){
        em.remove(em.merge(h));  
    }
    
    /**
     *
     * @return
     */
    public List<Hotel> findAll(){
        return em.createNamedQuery("Hotel.findAll", Hotel.class).getResultList();
    }
}
