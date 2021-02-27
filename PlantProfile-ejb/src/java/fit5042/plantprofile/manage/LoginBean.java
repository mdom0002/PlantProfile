/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.manage;


import fit5042.plantprofile.entities.PPUser;
import fit5042.plantprofile.inface.Login;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Medo
 */

@Stateless
public class LoginBean implements Login{
    @PersistenceContext(unitName = "PlantProfile-ejbPU")
    private EntityManager em;
    
    
    @Override
    public String login(String contact, String password) {
       
       List<PPUser> users = em.createNamedQuery(PPUser.GET_ALL_QUERY_NAME).getResultList();
        
        if(users != null)
        {
        for(PPUser user:users)
        {
            if(user.getContact().equals(contact))
                if(user.getPassword().equals(password))
                    return user.getUserType();
        } }
       
        return "index.xhtml";
    }
}
