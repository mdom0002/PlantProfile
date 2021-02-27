/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.controllers;

import fit5042.plantprofile.controllers.Plant;
import fit5042.plantprofile.mbeans.PlantDetailsManagedBean;
import fit5042.plantprofile.mbeans.PlantManagedBean;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Medo
 */

@RequestScoped
@Named("addPlantDetails")
public class AddPlantDetail {

    @ManagedProperty(value="#{plantDetailsManagedBean}")
    PlantDetailsManagedBean plantDetailsManagedBean;
    private boolean showForm = true;
    private fit5042.plantprofile.entities.PlantDetails plant;
    PlantDetailsApplication app;
    
    public boolean isShowForm() {
        return showForm;
    }

    public void setShowForm(boolean showForm) {
        this.showForm = showForm;
    }

    public fit5042.plantprofile.entities.PlantDetails getPlant() {
        return plant;
    }

    public void setPlant(fit5042.plantprofile.entities.PlantDetails plant) {
        this.plant = plant;
    }
    
    public AddPlantDetail()
    {
        ELContext context = FacesContext.getCurrentInstance().getELContext();

        app = (PlantDetailsApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "plantDetailsApplication"); 
        //instantiate plantManagedBean
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        plantDetailsManagedBean = (PlantDetailsManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "plantDetailsManagedBean");
    }


    public void addPlantDetails(PlantDetails plantDetails,int pid,String contact)
    {
        try
        {
            plantDetailsManagedBean.addPlantDetails(plantDetails, pid,contact);
            app.searchAll();
           // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Plant Details has been added succesfully"));
            
       }
       catch (Exception ex)
       {
           
       }
        showForm = true;
    }
    
}
