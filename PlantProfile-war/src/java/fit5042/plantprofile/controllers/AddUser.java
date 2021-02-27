/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.controllers;

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
@Named("addUser")
public class AddUser {
    @ManagedProperty(value="#{userManagedBean}") 
    UserManagedBean userManagedBean;
    
    private boolean showForm = true;

    private User user;
    
    UserApplication app;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public boolean isShowForm() {
        return showForm;
    }
    
    public AddUser() 
    {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app  = (UserApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "userApplication");
        
        //instantiate userManagedBean
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "userManagedBean");
    }
    
    public void addUser(fit5042.plantprofile.controllers.User localUser,String type) {
       try
       {
            //add this user to db via EJB
            userManagedBean.register(localUser,type);

           app.searchAll();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User has been added succesfully"));
         
       }
       catch (Exception ex)
       {
           String message = "failed" + ex.getMessage();
       }
        showForm = true;
    }
    
    
}
