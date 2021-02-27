/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.inface;

import fit5042.plantprofile.entities.Plant;
import fit5042.plantprofile.entities.PlantDetails;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Medo
 */
@Remote
public interface PlantFunctions {
    
    public Plant findPlantByPid(int pid) throws Exception;    
    public List<Plant> getAllPlants() throws Exception; 
    public List<Plant> findPlantByFilter(String name, double temperature, String color) throws Exception;
    public List<PlantDetails> findPlantDetailsByPid(int pid) throws Exception;   
    public void addPlant(Plant plant,String contact) throws Exception;    
    public void updatePlant(Plant plant) throws Exception;    
    public void removePlant(int pid) throws Exception;
    public Plant findPlantBySerialNumber(int serialNumber) throws Exception;
    public List<Plant> searchPlantByHeight(double heightMin,double heightMax) throws Exception;
    public List<Plant> searchPlantByColor(String color) throws Exception;

    
    
}
