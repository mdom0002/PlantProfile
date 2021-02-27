/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.mbeans;

import fit5042.plantprofile.entities.Plant;
import fit5042.plantprofile.entities.PlantDetails;
import fit5042.plantprofile.inface.PlantDetailsFunctions;
import fit5042.plantprofile.inface.PlantFunctions;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Medo
 */
@ManagedBean(name = "plantDetailsManagedBean")
@SessionScoped
public class PlantDetailsManagedBean implements Serializable{
  
    @EJB
    PlantDetailsFunctions plantDetailsFunctions; 
    
    public PlantDetailsManagedBean()
    {
        
    }
    
    public List<PlantDetails> getAllPlantDetails() {
        try {
            List<PlantDetails> plantDetails = plantDetailsFunctions.getAllPlantDetails();
            return plantDetails;
        } catch (Exception ex) {
        }
        return null;
    }

    
    public List<PlantDetails> getAllPlantDetailsByPid(int pid) {
        try {
            List<PlantDetails> plantDetails = plantDetailsFunctions.findPlantDetailsByPid(pid);
            return plantDetails;
        } catch (Exception ex) {
            Logger.getLogger(PlantDetailsManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
        
    

    public void addPlantDetails(fit5042.plantprofile.controllers.PlantDetails localPlant,int pid,String contact) 
    { 
        PlantDetails plantDetails = convertPlantDetailsToEntity(localPlant);
        try {
             plantDetailsFunctions.addPlantDetails(plantDetails,pid,contact);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Plant details has been added succesfully"));
        } catch (Exception ex) {
        }
    }
    
    public void addPlantDetails(PlantDetails plantDetails,int pid,String contact) 
    {
        try {
             plantDetailsFunctions.addPlantDetails(plantDetails,pid,contact);
        } catch (Exception ex) {
        }
    }
    
    public List<PlantDetails> findPlantDetailsByPid(int pid)
    {
        try {
            return plantDetailsFunctions.findPlantDetailsByPid(pid);
        } catch (Exception ex) {
            Logger.getLogger(PlantDetailsManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public List<Plant> findPlantByFilter(double height, double price) {
        try{
            return plantDetailsFunctions.findPlantDetailByFilter(height, price);
        }
        catch(Exception e) { }
        return null;
    }
    
    public void removePlantDetails(int pid) {
        try {
            
            plantDetailsFunctions.removePlantDetails(pid);
            
        } catch (Exception ex) {
            Logger.getLogger(PlantDetailsManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public void updatePlantDetails(PlantDetails plantDetails) {
        try {
            plantDetailsFunctions.updatePlantDetails(plantDetails);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Plant has been updated succesfully"));
        } catch (Exception ex) {
            Logger.getLogger(PlantDetailsManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Plant findPlantBySerialNumber(int serialNumber) {
        try {
            return plantDetailsFunctions.findPlantBySerialNumber(serialNumber);
        } catch (Exception ex) {
            Logger.getLogger(PlantDetailsManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    public PlantDetails convertPlantDetailsToEntity(fit5042.plantprofile.controllers.PlantDetails localPlant) {
        
        try
        {
            PlantDetails pd = new PlantDetails();
            double height = localPlant.getHeight();
            String location = localPlant.getLocation();
            int stock = localPlant.getStock();
            pd.setHeight(height);
            //pd.setPrice(price);
            pd.setLocation(location);
            pd.setStock(stock);
            return pd;
        }
        catch (Exception ex)
        {
            Logger.getLogger(PlantDetailsManagedBean.class.getName()).log(Level.SEVERE, null, ex); 
        }
        return null;
    }

    
}
