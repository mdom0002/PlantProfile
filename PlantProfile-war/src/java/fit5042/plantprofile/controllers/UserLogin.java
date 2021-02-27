/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.controllers;

import fit5042.plantprofile.mbeans.LoginManagedBean;
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
@Named("userLogin")
public class UserLogin {
    @ManagedProperty(value="#{loginManagedBean}") 
    LoginManagedBean loginManagedBean;
     private boolean showForm = true;
    LoginApplication app;
    public boolean isShowForm() {
        return showForm;
    }
    
    public UserLogin() 
    {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app  = (LoginApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "loginApplication");
        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        loginManagedBean = (LoginManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "loginManagedBean");
    }
    /*
    public String login(fit5042.plantprofile.controllers.Login contact,fit5042.plantprofile.controllers.Login pw) {
      String url = "";
        try
       {
            url = loginManagedBean.login(contact, pw);
            
       }
       catch (Exception ex)
       {
           
       }
        showForm = true;
        return url;
    } */
}
