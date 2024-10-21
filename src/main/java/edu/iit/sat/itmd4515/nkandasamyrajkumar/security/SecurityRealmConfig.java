/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

/**
 *
 * @author naveenarajkumar
 */
@Named
@ApplicationScoped
@DatabaseIdentityStoreDefinition(dataSourceLookup = "java:app/jdbc/itmd4515DS",
        callerQuery = "select PASSWORD from SECURE_USER where USERNAME = ?",
        groupsQuery = "select GROUPNAME from SECURE_USER_GROUPS where USERNAME = ?")
public class SecurityRealmConfig {
    
}
