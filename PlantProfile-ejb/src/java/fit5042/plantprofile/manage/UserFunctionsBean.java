/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.manage;

import fit5042.plantprofile.entities.Plant;
import fit5042.plantprofile.entities.PPUser;
import fit5042.plantprofile.inface.UserFunctions;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Medo
 */
@Stateless
public class UserFunctionsBean implements UserFunctions {
    
    @PersistenceContext(unitName = "PlantProfile-ejbPU")
    private EntityManager em;
    
    @Override
    public void register(PPUser user,String type) throws Exception {        
         String contact = user.getContact();
           List<PPUser> users = em.createNamedQuery(PPUser.GET_ALL_QUERY_NAME).getResultList();
           for(PPUser c:users)
           {
               if(c.getContact().equals(contact))
                   throw new Exception("already exists");
           }
           if(users.size() == 0)
            user.setUserId(111);
           else
            user.setUserId(users.get(0).getUserId()+ 1);
           user.setUserType(type);
           em.persist(user);
       
    }
    
    @Override
    public PPUser findUserById(int uid) throws Exception {
        return em.find(PPUser.class, uid);
    }
    
    @Override
    public List<PPUser> getAllUsers() throws Exception {
        return em.createNamedQuery(PPUser.GET_ALL_QUERY_NAME).getResultList();
    
    }
    
    @Override
    public List<PPUser> getAllCustomers() throws Exception {
        return em.createNamedQuery(PPUser.GET_BY_TYPE).setParameter("type", "customer").getResultList();
    
    }
    
    @Override
    public List<PPUser> getAllStaff() throws Exception {
        return em.createNamedQuery(PPUser.GET_BY_TYPE).setParameter("type", "staff").getResultList();
    
    }
    
    @Override
    public PPUser findUserByContact(String contact) throws Exception {
        List<PPUser> users = em.createNamedQuery(PPUser.FIND_BY_CONTACT).setParameter("contact", contact).getResultList();

        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }
    
    @Override
    public void removeUser(int uid) throws Exception{
        PPUser user=findUserById(uid);
     List<PPUser> users = em.createNamedQuery(PPUser.GET_ALL_QUERY_NAME).getResultList();
        for(PPUser c: users)
        {
            if(c.getUserId()== uid)
                em.remove(c);
        }
    }
    
    @Override
    public void updateUser(PPUser user) throws Exception {        
        em.merge(user);
    }
 
   

}
