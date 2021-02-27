/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.controllers;

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
@Named("addPlant")
public class AddPlant {

    @ManagedProperty(value="#{plantManagedBean}")
    PlantManagedBean plantManagedBean;
    private boolean showForm = true;
    private fit5042.plantprofile.entities.Plant plant;
    PlantApplication app;
    public boolean isShowForm() {
        return showForm;
    }

    public void setShowForm(boolean showForm) {
        this.showForm = showForm;
    }

    public fit5042.plantprofile.entities.Plant getPlant() {
        return plant;
    }

    public void setPlant(fit5042.plantprofile.entities.Plant plant) {
        this.plant = plant;
    }
    
    public AddPlant()
    {
        
        ELContext context = FacesContext.getCurrentInstance().getELContext();

        app  = (PlantApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "plantApplication");
        //instantiate plantManagedBean
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        plantManagedBean = (PlantManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "plantManagedBean");
    }

    public void addPlant(Plant plant,String contact)
    {
        try
        {
            //ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            //String username = ec.getRemoteUser();
            plantManagedBean.addPlant(plant,contact);
            app.searchAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Plant has been added succesfully"));
            
       }
       catch (Exception ex)
       {
           
       }
        showForm = true;
        
    }

 

}