/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.controllers;

import fit5042.plantprofile.entities.Address;
import fit5042.plantprofile.entities.PPUser;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.el.ELContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Medo
 */
@Named(value = "userController")
@RequestScoped
public class UserController {
    private Integer userIndex;
    private PPUser user;
    private Address address;

    public Integer getUserIndex() {
        return userIndex;
    }

    public void setUserIndex(Integer userIndex) {
        this.userIndex = userIndex;
    }
    
    public UserController() {
        userIndex = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("userIndex"));
        user = getUser();
    }
    
    public PPUser getUser() {
        if (user == null) {
            ELContext context
                    = FacesContext.getCurrentInstance().getELContext();
            UserApplication app
                    = (UserApplication) FacesContext.getCurrentInstance()
                            .getApplication()
                            .getELResolver()
                            .getValue(context, null, "userApplication");
            return app.getUsers().get(--userIndex);
        }
        return user;
    }
    
}
