/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.controllers;

import java.util.List;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Medo
 */
@RequestScoped
@Named("searchPlant")
public class SearchPlant {
    
    private boolean showForm = true;
    private Plant plant;
    PlantApplication app;
    private int searchById;
    private double searchByHeightMax;
    private double searchByHeightMin;
    private String searchByColor;

    public SearchPlant(Plant plant, PlantApplication app, int searchById) {
        this.plant = plant;
        this.app = app;
        this.searchById = searchById;
    }

    public double getSearchByHeightMax() {
        return searchByHeightMax;
    }

    public void setSearchByHeightMax(double searchByHeightMax) {
        this.searchByHeightMax = searchByHeightMax;
    }

    public double getSearchByHeightMin() {
        return searchByHeightMin;
    }

    public void setSearchByHeightMin(double searchByHeightMin) {
        this.searchByHeightMin = searchByHeightMin;
    }

    public String getSearchByColor() {
        return searchByColor;
    }

    public void setSearchByColor(String searchByColor) {
        this.searchByColor = searchByColor;
    }

    
    
    

    public boolean isShowForm() {
        return showForm;
    }

    public void setShowForm(boolean showForm) {
        this.showForm = showForm;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public PlantApplication getApp() {
        return app;
    }

    public void setApp(PlantApplication app) {
        this.app = app;
    }

    public int getSearchById() {
        return searchById;
    }

    public void setSearchById(int searchById) {
        this.searchById = searchById;
    }

    

   
    
    
    public SearchPlant() {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app = (PlantApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "plantApplication");
        
        app.updatePlantList();
                
    }
    
    
    public void searchPlantByHeight(double heightMin,double heightMax) 
    {
       try
       {
           if(heightMax == 0.0)
               heightMax = 10000;
            app.searchPlantByHeight(heightMin,heightMax);
       }
       catch (Exception ex)
       {
           
       }
       showForm = true;
    }
    
    public void searchPlantByColor(String color) 
    {
       try
       {
           if(color != null)
            app.searchPlantByColor(color);
       }
       catch (Exception ex)
       {
           
       }
       showForm = true;
    }
    
    public void findPlantByPid(int pid) 
    {
       try
       {
           app.findPlantByPid(pid);
       }
       catch (Exception ex)
       {
           
       }
       showForm = true;

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
