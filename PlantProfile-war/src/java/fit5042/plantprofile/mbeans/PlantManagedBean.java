/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.mbeans;


import fit5042.plantprofile.entities.Plant;
import fit5042.plantprofile.entities.PlantDetails;
import fit5042.plantprofile.inface.PlantFunctions;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
/**
 *
 * @author Medo
 */
@ManagedBean(name = "plantManagedBean")
@SessionScoped
public class PlantManagedBean implements Serializable{
  
    @EJB
    PlantFunctions plantFunctions; 
    private Part file;
    public PlantManagedBean()
    {
        
    }
    
    public List<Plant> getAllPlants() {
        try {
            List<Plant> plants = plantFunctions.getAllPlants();           
            return plants;
        } catch (Exception ex) {
        }
        return null;
    }
 

    public void addPlant(fit5042.plantprofile.controllers.Plant localPlant,String contact) {
        Plant plant = convertPlantToEntity(localPlant);
        try {
            
            plantFunctions.addPlant(plant,contact);
        } catch (Exception ex) {
            Logger.getLogger(PlantManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void addPlant(Plant plant,String contact) 
    {
        try {
             plantFunctions.addPlant(plant,contact);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Plant has been added succesfully"));
        } catch (Exception ex) {
            Logger.getLogger(PlantManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

    
    public Plant findPlantByPid(int pid)
    {
        try {
            return plantFunctions.findPlantByPid(pid);
        } catch (Exception ex) {
            Logger.getLogger(PlantManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    
    public List<PlantDetails> findPlantDetailsByPid(int pid)
    {
        try {
            return plantFunctions.findPlantDetailsByPid(pid);
        } catch (Exception ex) {
            Logger.getLogger(PlantManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
     public List<Plant> searchPlantByHeight(double heightMin,double heightMax) 
    {
        try {
            return plantFunctions.searchPlantByHeight(heightMin,heightMax);
        } catch (Exception ex) {
            Logger.getLogger(PlantManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public List<Plant> searchPlantByColor(String color) 
    {
        try {
            return plantFunctions.searchPlantByColor(color);
        } catch (Exception ex) {
            Logger.getLogger(PlantManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public List<Plant> findPlantByFilter(String name, double temperature, String color) {
        try{
            return plantFunctions.findPlantByFilter(name, temperature, color);
        }
        catch(Exception e) { }
        return null;
    }
    
    public void removePlant(int pid) {
        try {
            
            plantFunctions.removePlant(pid);
            
        } catch (Exception ex) {
            Logger.getLogger(PlantManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public void updatePlant(Plant plant) {
        try {
            plantFunctions.updatePlant(plant);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Plant has been updated succesfully"));
        } catch (Exception ex) {
            Logger.getLogger(PlantManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Plant findPlantBySerialNumber(int serialNumber) {
        try {
            return plantFunctions.findPlantBySerialNumber(serialNumber);
        } catch (Exception ex) {
            Logger.getLogger(PlantManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public Plant convertPlantToEntity(fit5042.plantprofile.controllers.Plant localPlant) {
        
        try
        {
            Plant plant = new Plant(); //entity
            String name = localPlant.getName();
            double temperature = localPlant.getTemperature();
            String color = localPlant.getColor();
            plant.setName(name);
            plant.setColor(color);
            plant.setTemperature(temperature);
            return plant;
        }
        catch (Exception ex)
        {
            Logger.getLogger(PlantManagedBean.class.getName()).log(Level.SEVERE, null, ex); 
        }
        return null;
    }
    




}
