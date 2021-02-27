/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.controllers;

import fit5042.plantprofile.entities.PPUser;
import fit5042.plantprofile.mbeans.UserManagedBean;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Medo
 */
@RequestScoped
@Named("register")
public class Register {
    
    @ManagedProperty(value="#{userManagedBean}") 
    UserManagedBean userManagedBean;
    private boolean showForm = true;
    
    private PPUser user;
    UserApplication app;

    public PPUser getUser() {
        return user;
    }

    public void setUser(PPUser user) {
        this.user = user;
    }
    
    public boolean isShowForm() {
        return showForm;
    }
    
    public Register() {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();
        
        app  = (UserApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "userAplication");
        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "userManagedBean");
    }
    
    public Register(PPUser c,String type) {
        try {
            userManagedBean.register(c,type);
            app.searchAll();
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User has been added succesfully"));
           
        }
        catch(Exception e)
        {
           String message = "failed" + e.getMessage(); 
        }
        showForm = true;
    }
            

            
            
}
