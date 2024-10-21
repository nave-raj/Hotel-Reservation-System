/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.web;

import edu.iit.sat.itmd4515.nkandasamyrajkumar.security.User;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author naveenarajkumar
 */
@Named
@RequestScoped
public class LoginController {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());

    @Inject
    SecurityContext securityContext;

    @Inject
    FacesContext facesContext;

    /* declaring the model */
    private User user;

    /**
     *
     */
    public LoginController() {
    }

    @PostConstruct
    private void postContruct() {
        LOG.info("LoginController.postConstruct");
        /* instantiating the declared model */
        user = new User();
    }

    /* helper method */

    /**
     *
     * @return
     */

    public String getAuthenticatedUser() {
        //facesContext.getExternalContext().getRemoteUser();
        return securityContext.getCallerPrincipal().getName();
    }

    /**
     *
     * @return
     */
    public boolean isAdmin() {
        return securityContext.isCallerInRole("ADMIN_ROLE");
    }

    /**
     *
     * @return
     */
    public boolean isGuest() {
        return securityContext.isCallerInRole("GUEST_ROLE");
    }

    /**
     *
     * @return
     */
    public boolean isStaff() {
        return securityContext.isCallerInRole("STAFF_ROLE");
    }

    /* action methods */

    /**
     *
     * @return
     */

    public String doLogin() {
        LOG.info("LoginController.doLogin");

        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        Credential cred = new UsernamePasswordCredential(this.user.getUserName(), new Password(this.user.getPassword()));

        AuthenticationStatus status = securityContext.authenticate(request, response, AuthenticationParameters.withParams().credential(cred));

        switch (status) {
            case SUCCESS:
                LOG.info(status.toString());
                break;
            case SEND_FAILURE:
                LOG.info("FAILURE!" + status.toString());
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Username or Password", "Please try again!"));
                return null; 
            case NOT_DONE:
                LOG.info("NOT DONE!" + status.toString());
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Authentication Failed", "Please try again!"));
                return null;
            case SEND_CONTINUE:
                LOG.info(status.toString());
                break;
        }
        return "/welcome.xhtml?faces-redirect=true";
    }

    /**
     *
     * @return
     */
    public String doLogout() {
        LOG.info("LoginController.doLogout");
        try {
            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            request.logout();

        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return "/login.xhtml?faces-redirect=true";
    }

    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

}
