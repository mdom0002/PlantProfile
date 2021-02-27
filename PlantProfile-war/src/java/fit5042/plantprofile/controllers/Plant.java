/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.controllers;

import fit5042.plantprofile.entities.PlantDetails;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Medo
 */
@RequestScoped
@Named(value = "allplant")
public class Plant implements Serializable {
 
    
    private String name;
    private double temperature;
    private String color;
    public Plant() {
    
    name = ""; 
    temperature = 0.0;
    color = "";
    
    }

    public Plant(String name, double temperature, String color) {
      
        this.name = name;
        this.temperature = temperature;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    @Override
    public String toString() {
        return "Plant{" + "name=" + name + ", temperature=" + temperature + ", color=" + color + '}';
    }
    
}
