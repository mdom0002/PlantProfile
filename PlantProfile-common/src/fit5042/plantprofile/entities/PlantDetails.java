/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 *
 * @author Medo
 */

@Entity
@NamedQueries({
    @NamedQuery(name = PlantDetails.GET_ALL_QUERY_NAME, query = "SELECT p FROM PlantDetails p order by p.serialNumber desc"),
    @NamedQuery(name = PlantDetails.GET_BY_SERIALNUMBER, query = "SELECT p FROM PlantDetails p where p.serialNumber = :serialNumber"),
    @NamedQuery(name = PlantDetails.GET_PLANT, query = "SELECT p FROM Plant p where p.pid = :pid")})

public class PlantDetails implements Serializable {
    
    public static final String GET_ALL_QUERY_NAME = "PlantDetails.getAll";
    public static final String GET_BY_SERIALNUMBER = "PlantDetails.findBySerialNumber";
    public static final String GET_PLANT = "PlantDetails.findByPid";
    
    private int serialNumber;
    private double height;
    private String location;
    private int stock;
    
    
    private Plant plant;
    private PPUser user;
            
    public PlantDetails() {
        this.serialNumber = 0;
        this.height = 0.0;
        this.location = "";
       this.stock = 0;
    }
    public PlantDetails(int serialNumber, double height, String location) {
        this.serialNumber = serialNumber;
        this.height = height;
        this.location = location;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "plant_serial_number")
    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
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


    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    
    
    @ManyToOne
   @JoinColumn(name = "plant_id")
    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    @ManyToOne
   @JoinColumn(name = "user_id")
    public PPUser getUser() {
        return user;
    }

    public void setUser(PPUser user) {
        this.user = user;
    }


    
    @Override
    public String toString() {
        return "PlantDetails{" + "serialNumber=" + serialNumber + ", height=" + height + ", location=" + location + ", stock=" + stock +  '}';
    }
    
    
    
    
 
    
}
