/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.controllers;

import fit5042.plantprofile.entities.Plant;
import fit5042.plantprofile.entities.PlantDetails;
import java.util.List;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Medo
 */
@Named(value = "plantDetailsController")
@RequestScoped
public class PlantDetailsController { 
    
    private Integer pid;
    private List<PlantDetails> plant;

    public Integer getPlantIndex() {
        return pid;
    }

    public void setPlantIndex(Integer plantIndex) {
        this.pid = plantIndex;
    }
    
    
    
    public PlantDetailsController() {
        pid = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("plantid"));
        plant = this.getPlantDetails(); 
    }
    
    public List<PlantDetails> getPlantDetails() {
        if (plant == null) {
            ELContext context
                    = FacesContext.getCurrentInstance().getELContext();
            PlantDetailsApplication app
                    = (PlantDetailsApplication) FacesContext.getCurrentInstance()
                            .getApplication()
                            .getELResolver()
                            .getValue(context, null, "plantDetailsApplication");
           return app.getPlantDetails();
           
     
        }
        return plant;  
    }
    
}
