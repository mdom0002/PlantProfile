/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.inface;

import fit5042.plantprofile.entities.Plant;
import fit5042.plantprofile.entities.PPUser;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Medo
 */
@Remote
public interface UserFunctions {
    
    public PPUser findUserById(int uid) throws Exception;
    
    public List<PPUser> getAllUsers() throws Exception;
    
    public List<PPUser> getAllCustomers() throws Exception;
    
    public List<PPUser> getAllStaff() throws Exception;
    
    public PPUser findUserByContact(String contact) throws Exception;
   
    public void register(PPUser user,String type) throws Exception;
    
    public void updateUser(PPUser user) throws Exception;
    
    public void removeUser(int uid) throws Exception;
    
    
}
