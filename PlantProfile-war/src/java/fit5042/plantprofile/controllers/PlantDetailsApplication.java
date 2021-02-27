/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.controllers;

import fit5042.plantprofile.entities.Plant;
import fit5042.plantprofile.entities.PlantDetails;
import fit5042.plantprofile.mbeans.PlantDetailsManagedBean;
import fit5042.plantprofile.mbeans.PlantManagedBean;
import java.util.ArrayList;
import java.util.List;
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
@Named(value = "plantDetailsApplication")
@ApplicationScoped
public class PlantDetailsApplication {
    
    @ManagedProperty(value="#{plantDetailsManagedBean}") 
    PlantDetailsManagedBean plantDetailsManagedBean;
    
    private ArrayList<PlantDetails> plantDetails;

    private boolean showForm = true;

    public ArrayList<PlantDetails> getPlantDetails() {
        return plantDetails;
    }
    public void setPlantDetails(ArrayList<PlantDetails> plantDetails) {
        this.plantDetails = plantDetails; }
  
    public boolean isShowForm() {
        return showForm; }

 
    
    public PlantDetailsApplication() throws Exception {       
       
        plantDetails = new ArrayList<>();
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        plantDetailsManagedBean = (PlantDetailsManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "plantDetailsManagedBean");
         
        updatePlantDetailsList();
       
    }
   
    
    public void updatePlantDetailsList()
    {
        if (plantDetails != null && plantDetails.size() > 0)
        {
            
        }
        else
        { 
            plantDetails.clear();
            for (PlantDetails plant : plantDetailsManagedBean.getAllPlantDetails())
            {
                plantDetails.add(plant);
                
            }
            setPlantDetails(plantDetails);
        }
    }
    public void findPlantDetailsByPid(int pid)
    {
        plantDetails.clear();
        for(PlantDetails pd: plantDetailsManagedBean.getAllPlantDetailsByPid(pid))
        {
        plantDetails.add(pd);
        }
        setPlantDetails(plantDetails);
    }
    
        
    
    
    public void removePlantDetails(int serialNumber) {
       try
       {
            plantDetailsManagedBean.removePlantDetails(serialNumber);
            searchAll();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(serialNumber +" has been deleted succesfully"));     
       }
       catch (Exception ex)
       {
           
       }
       showForm = true;

    }
    public void searchAll()
    {
        plantDetails.clear();
        
        for (PlantDetails plant : plantDetailsManagedBean.getAllPlantDetails())
        {
            plantDetails.add(plant);
            
        }
        
        setPlantDetails(plantDetails);
        
    
    }
    

    
 
}
