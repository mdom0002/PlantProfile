/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.mbeans;

import fit5042.plantprofile.entities.Address;
import fit5042.plantprofile.entities.PPUser;
import fit5042.plantprofile.inface.UserFunctions;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Medo
 */
@ManagedBean(name = "userManagedBean",eager = true)
@SessionScoped
public class UserManagedBean implements Serializable{
    @EJB
    UserFunctions userFunction;
    
    public UserManagedBean()
    {
        
    }
    
    public PPUser findUserById(int uid)
    {
        try
        {
            return userFunction.findUserById(uid);
        }
        catch(Exception e)
        {
            
        }
        return null;
    }
    
    public PPUser findUserByContact(String contact){
        try{
            return userFunction.findUserByContact(contact);
        }
        catch(Exception e) {
            
        }
        return null;
    }
 
    
    public void register(fit5042.plantprofile.controllers.User localUser,String type){
        
        PPUser user = convertUserToEntity(localUser);
        try{
            userFunction.register(user,type);
        }
        catch(Exception e)
        {
            
        }
    }
    
    public void register(PPUser user,String type){
        try{
            userFunction.register(user,type);
        }
        catch(Exception e)
        {
            
        }
    }
    
    public void updateUser(PPUser user)
    {
        try{
            
            userFunction.updateUser(user);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User has been updated succesfully"));
        }
        catch(Exception e)
        {
          
        }
    }
    public void removeUser(int uid)
    {
        try{
            userFunction.removeUser(uid);
        }
        catch(Exception e)
        {
            
        }
    }
    
    public List<PPUser> getAllUsers() {
        try {
            List<PPUser> users = userFunction.getAllUsers();
            return users;
        } catch (Exception ex) {
            Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<PPUser> getAllCustomers() {
        try {
            List<PPUser> users = userFunction.getAllCustomers();
            return users;
        } catch (Exception ex) {
            Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public PPUser convertUserToEntity(fit5042.plantprofile.controllers.User localUser) {
        
        try
        {
            PPUser user = new PPUser();
            String firstName = localUser.getFirstName();
            String lastName = localUser.getLastName();
            Address address = new Address();
            user.setAddress(address);
            String streetNumber = localUser.getStreetNumber();
            String streetAddress = localUser.getStreetAddress();
            String suburb = localUser.getSuburb();
            String postcode = localUser.getPostcode();
            String state = localUser.getState();
            String contact = localUser.getContact();
            String password = localUser.getPassword();
            String email = localUser.getEmail();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.getAddress().setStreetNumber(streetNumber);
            user.getAddress().setStreetAddress(streetAddress);
            user.getAddress().setSuburb(suburb);
            user.getAddress().setPostcode(postcode);
            user.getAddress().setState(state);
            user.setContact(contact);
            user.setPassword(password);
            user.setEmail(email);
            return user;
        }
        catch (Exception ex)
        {
            Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex); 
        }
        return null;
    }
}
