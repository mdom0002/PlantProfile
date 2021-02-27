/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.controllers;

import fit5042.plantprofile.entities.Plant;
import fit5042.plantprofile.entities.PlantDetails;
import fit5042.plantprofile.mbeans.PlantManagedBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
@Named(value = "plantApplication")
@ApplicationScoped
public class PlantApplication {
    
    @ManagedProperty(value="#{plantManagedBean}") 
    PlantManagedBean plantManagedBean;
    
    private ArrayList<Plant> plants;

    private boolean showForm = true;

    public boolean isShowForm() {
        return showForm; }

    public ArrayList<Plant> getPlants() {
        return plants;
    }

    public void setPlants(ArrayList<Plant> plants) {
        this.plants = plants;
    }

    
    
    public PlantApplication() throws Exception {       
        plants = new ArrayList<>();
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        plantManagedBean = (PlantManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "plantManagedBean");
         
        this.updatePlantList();
       
    }
    
    public void updatePlantList()
    {
        if (plants != null && plants.size() > 0)
        {
            
        }
        else
        { 
            plants.clear();
            for (Plant plant : plantManagedBean.getAllPlants())
            {
                
                plants.add(plant);
                
            }
            
            setPlants(plants);
        }
        
        
    }
     
    public void removePlant(int pid) {
       try
       {
            plantManagedBean.removePlant(pid);
            searchAll();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(pid +" has been deleted succesfully"));     
       }
       catch (Exception ex)
       {
           
       }
       showForm = true;

    }
    
    
    public void findPlantByPid(int pid)
    {
        plants.clear();
        plants.add(plantManagedBean.findPlantByPid(pid));
        
    }
    
    public void searchPlantByHeight(double heightMin,double heightMax)
    {
        plants.clear();
        
        for (fit5042.plantprofile.entities.Plant plant : plantManagedBean.searchPlantByHeight(heightMin,heightMax))
        {
            plants.add(plant);
        }
        
        setPlants(plants);
    }
    
    public void searchPlantByColor(String color)
    {
        plants.clear();
        
        for (fit5042.plantprofile.entities.Plant plant : plantManagedBean.searchPlantByColor(color))
        {
            plants.add(plant);
        }
        
        setPlants(plants);
    }

    
    public void searchAll()
    {
        plants.clear();
        
        for (Plant plant : plantManagedBean.getAllPlants())
        {
            plants.add(plant);
            
        }
        
        setPlants(plants);
        
    }
   

   
}
