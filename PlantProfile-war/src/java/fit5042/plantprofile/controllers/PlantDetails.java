/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.controllers;

import fit5042.plantprofile.mbeans.PlantDetailsManagedBean;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Medo
 */
@RequestScoped
@Named(value = "allplantd")
public class PlantDetails implements Serializable {
    
    private double height;
    private String location;
    private int stock;
    private boolean editmode;
    private int number;
    public PlantDetails()
    {
        height = 0.0;
        location = "";
        stock = 0;
        number =0;
    }

    public PlantDetails(double height, String location) {
        this.height = height;
        this.location = location;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    

    @Override
    public String toString() {
        return "PlantDetails{" + "height=" + height + ", location=" + location + '}';
    }
    
    
}
