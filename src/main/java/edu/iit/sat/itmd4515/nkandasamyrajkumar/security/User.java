/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.security;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author naveenarajkumar
 */
@Entity
@Table(name = "SECURE_USER")
@EntityListeners(UserPasswordHash.class)
@NamedQuery(name = "User.findAll", query = "select user from User user")
public class User {

    @Id
    @NotBlank(message = "Please enter the username")
    private String userName;
    @NotBlank(message = "Please enter the password")
    private String password;

    /* owning side - M:M relationship between user and groups */
    @ManyToMany
    @JoinTable(name = "SECURE_USER_GROUPS", joinColumns = @JoinColumn(name = "USERNAME"),
            inverseJoinColumns = @JoinColumn(name = "GROUPNAME"))
    private List<Group> groups = new ArrayList<>();

    /**
     *
     * @param userName
     * @param password
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     *
     */
    public User() {
    }
    
    /* helper methods */ 

    /**
     *
     * @param g
     */
 
    public void addGroup(Group g){
        this.groups.add(g);
        g.getUsers().add(this);
    }
    
    /**
     *
     * @param g
     */
    public void removeGroup(Group g){
        this.groups.remove(g);
        g.getUsers().remove(this);
    }

    /**
     * Get the value of userName
     *
     * @return the value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the value of userName
     *
     * @param userName new value of userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     *
     * @param groups
     */
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
    
    

}
