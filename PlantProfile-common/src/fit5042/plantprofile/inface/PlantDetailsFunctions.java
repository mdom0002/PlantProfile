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
public interface PlantDetailsFunctions {
    
    public List<PlantDetails> findPlantDetailsByPid(int pid) throws Exception; 
    public List<PlantDetails> getAllPlantDetails() throws Exception;
    public List<Plant> findPlantDetailByFilter(double height, double price) throws Exception;
    public PlantDetails findPlantDetailsBySerialNumber(int serialNumber) throws Exception;     
    public void addPlantDetails(PlantDetails plantDetail,int pid,String contact) throws Exception;   
    public void updatePlantDetails(PlantDetails plantDetails) throws Exception; 
    public void removePlantDetails(int pid) throws Exception;
    public Plant findPlantBySerialNumber(int serialNumber) throws Exception;
    
}
