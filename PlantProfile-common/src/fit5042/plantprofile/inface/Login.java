/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.inface;

import javax.ejb.Remote;

/**
 *
 * @author Medo
 */
@Remote
public interface Login {
    public String login(String contact, String password) throws Exception;
    
}
