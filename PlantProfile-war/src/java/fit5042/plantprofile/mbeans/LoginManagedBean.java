/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.mbeans;

import fit5042.plantprofile.inface.Login;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Medo
 */
@ManagedBean(name = "loginManagedBean")
@ViewScoped
public class LoginManagedBean implements Serializable{
    @EJB
    Login loginIn;
    
    String desc = "";
    
    public LoginManagedBean() {
        
        desc = "Plant profile is a web applicaton";
            }
   /*
    public String convertContactToEntity(fit5042.plantprofile.controllers.Login localContact) {
        
        try
        {
            String contact = localContact.getContact();
            
            return contact;
        }
        catch (Exception ex)
        {
            Logger.getLogger(PlantManagedBean.class.getName()).log(Level.SEVERE, null, ex); 
        }
        return null;
    }
    
    public String convertPasswordToEntity(fit5042.plantprofile.controllers.Login localPassword) {
        
        try
        {
            String pw = localPassword.getPassword();
            
            return pw;
        }
        catch (Exception ex)
        {
            Logger.getLogger(PlantManagedBean.class.getName()).log(Level.SEVERE, null, ex); 
        }
        return null;
    }
    
    public String login(fit5042.plantprofile.controllers.Login con, fit5042.plantprofile.controllers.Login pw)
    {
       String contact = convertContactToEntity(con);
        String password = convertPasswordToEntity(pw);
        try {
            String user = loginIn.login(contact, password);
            if(user.equals("staff"))
                return "s_home.xhtml";
            else if(user.equals("customer"))
                return "home.xhtml";
        }
        catch(Exception e) {}
        return "invalid";
    } 
    */
    public String login(String contact, String pw)
    {
        try {
            String user = loginIn.login(contact, pw);
            if(user.equals("staff"))
                return "/staff/s_home.xhtml";
            else if(user.equals("customer"))
                return "/customer/c_home.xhtml";
        }
        catch(Exception e) {}
        return "index.xhtml";
    } 
    
     	public String logout() {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            return "/index.xhtml?faces-redirect=true";
}

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
        
    
}
