/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.controllers;

import fit5042.plantprofile.mbeans.LoginManagedBean;
import javax.el.ELContext;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Medo
 */
@Named(value = "loginApplication")
@ApplicationScoped
public class LoginApplication {
    @ManagedProperty(value = "#{loginManagedBean}")
    LoginManagedBean loginManagedBean;
    String url = "";
    String user = "";
    private boolean showForm = true;
    public boolean isShowForm() {
        return showForm;
    }
    
    public LoginApplication()
    {
        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        loginManagedBean = (LoginManagedBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "loginManagedBean");
        
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    /*
    public String Login(fit5042.plantprofile.controllers.Login contact,fit5042.plantprofile.controllers.Login password)
    {
        try{
            user = loginManagedBean.login(contact,password);
            if(user.equals("staff"))
                setUrl("s_home.xhtml");
            else if(user.equals("customer"))
                setUrl("home.xhtml");
        }
        catch(Exception e)
        {
            
        }
        showForm = true;
        
        return url;
    } */
    
}
