/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.controllers;

import fit5042.plantprofile.entities.PPUser;
import fit5042.plantprofile.mbeans.UserManagedBean;
import java.util.ArrayList;
import javax.el.ELContext;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Medo
 */

@Named(value = "userApplication")
@ApplicationScoped
public class UserApplication {
    
    @ManagedProperty(value="#UserApplication1n}") 
    UserManagedBean userManagedBean;
    
    private ArrayList<PPUser> users;
    private boolean showForm = true;
  
    public boolean isShowForm() {
        return showForm;
    }
    
    public UserApplication() throws Exception {       
        users = new ArrayList<>();
        
        //instantiate userManagedBean
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "userManagedBean");
        
        updateUserList();
    }

    public ArrayList<PPUser> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<PPUser> users) {
        this.users = users;
    }
    
    public void updateUserList() {
        
        if (users != null && users.size() > 0)
        {
            
        }
        else
        {
            users.clear();

            for (PPUser user : userManagedBean.getAllCustomers())
            {
                users.add(user);
            }

            setUsers(users);
        }
    }
    
    public void findUserById(int uid)
    {
       users.clear();
        
        users.add(userManagedBean.findUserById(uid));
    }
    
        
    public void findUserByContact(String contact)
    {
        users.clear();
        
        users.add(userManagedBean.findUserByContact(contact));
    }
    
    public void searchAll()
    {
        users.clear();
        
        for (PPUser user : userManagedBean.getAllUsers())
        {
            users.add(user);
        }
        
        setUsers(users);
    }
    
    public void removeUser(int uid) {
       try
       {
            userManagedBean.removeUser(uid);
            searchAll();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(uid +" has been deleted succesfully"));     
       }
       catch (Exception ex)
       {
           
       }
       showForm = true;

    }
    
}
