/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.entities;

import java.util.List;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @NamedQuery(name = Plant.GET_ALL_PLANTS, query = "SELECT p FROM Plant p order by p.pid desc"),
    @NamedQuery(name = Plant.GET_BY_PID, query = "SELECT p FROM Plant p where p.pid = :pid"),
    @NamedQuery(name = Plant.GET_ALL_PLANTDETAILS, query = "SELECT p FROM Plant p where p.plantDetails = :plantDetails")})
public class Plant implements Serializable{
    
    public static final String GET_ALL_PLANTS = "Plant.getAll";
    public static final String GET_BY_PID = "Plant.getByID";
    public static final String GET_ALL_PLANTDETAILS = "Plant.getAllPlantDetails";
    private int pid;
    private String name;
    private double temperature;
    private String color;
    private Set<PlantDetails> plantDetails;
    private PPUser user;
    
    public Plant()
    {
        this.pid = 0;
        this.name = "";
        this.temperature = 0.0;
        this.color = "";
        this.plantDetails = new HashSet<>();
        
    }

    public Plant(int pid, String name, double temperature, String color) {
        this.pid = pid;
        this.name = name;
        this.temperature = temperature;
        this.color = color;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "plant_id")
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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

    @OneToMany(targetEntity = PlantDetails.class,mappedBy = "plant")
    public Set<PlantDetails> getPlantDetails() {
        return plantDetails;
    }

    public void setPlantDetails(Set<PlantDetails> plantDetails) {
        this.plantDetails = plantDetails;
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
        return "Plant{" + "pid=" + pid + ", name=" + name + ", temperature=" + temperature + ", color=" + color + '}';
    }

    

   
    
    
}
