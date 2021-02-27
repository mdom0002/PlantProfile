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
@Named(value = "plantController")
@RequestScoped
public class PlantController { 
    
    private Integer pid;
    private Plant plant;

    public Integer getPlantIndex() {
        return pid;
    }

    public void setPlantIndex(Integer plantIndex) {
        this.pid = plantIndex;
    }
    
    
    
    public PlantController() {
        pid = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("plantid"));
        plant = this.getPlant(); 
    }
    
    public Plant getPlant() {
        if (plant == null) {
            ELContext context
                    = FacesContext.getCurrentInstance().getELContext();
            PlantApplication app
                    = (PlantApplication) FacesContext.getCurrentInstance()
                            .getApplication()
                            .getELResolver()
                            .getValue(context, null, "plantApplication");
            return app.getPlants().get(--pid); 
        }
        return plant;  
    }
    
}
