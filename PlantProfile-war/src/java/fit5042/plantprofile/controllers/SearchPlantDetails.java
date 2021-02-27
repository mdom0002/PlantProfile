/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Medo
 */
@RequestScoped
@Named("searchPlantDetails")
public class SearchPlantDetails {
    
    private boolean showForm = true;
    private PlantDetails plantDetails;
    PlantDetailsApplication app;
    private double searchByHeight;

    public SearchPlantDetails(PlantDetails plantDetails, double searchByHeight) {
        this.plantDetails = plantDetails;
        this.searchByHeight = searchByHeight;
    }

    
    
    

    public boolean isShowForm() {
        return showForm;
    }

    public void setShowForm(boolean showForm) {
        this.showForm = showForm;
    }

    public PlantDetails getPlantDetails() {
        return plantDetails;
    }

    public void setPlantDetails(PlantDetails plantDetails) {
        this.plantDetails = plantDetails;
    }

    public double getSearchByHeight() {
        return searchByHeight;
    }

    public void setSearchByHeight(double searchByHeight) {
        this.searchByHeight = searchByHeight;
    }

    
    
    
    
    public SearchPlantDetails() {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app = (PlantDetailsApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "plantDetailsApplication");
        
        app.updatePlantDetailsList();
                
    }

    
    public void searchAll() 
    {
       try
       {
           app.searchAll();
       }
       catch (Exception ex)
       {
           
       }
       showForm = true;
    }
    
}
