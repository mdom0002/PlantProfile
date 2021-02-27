/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.manage;

import fit5042.plantprofile.entities.PPUser;
import fit5042.plantprofile.entities.Plant;
import fit5042.plantprofile.inface.PlantFunctions;
import fit5042.plantprofile.entities.PlantDetails;
import fit5042.plantprofile.inface.PlantDetailsFunctions;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Medo
 */
@Stateless
public class PlantDetailsFunctionsBean implements PlantDetailsFunctions{

    @PersistenceContext(unitName = "PlantProfile-ejbPU")
    private EntityManager em;

    @Override
    public void addPlantDetails(PlantDetails plantDetail,int pid,String contact) throws Exception {
        List<PlantDetails> plantDetails =  em.createNamedQuery(PlantDetails.GET_ALL_QUERY_NAME).getResultList();
        if(plantDetails.size() == 0)
            plantDetail.setSerialNumber(1111);
        else 
            plantDetail.setSerialNumber(plantDetails.get(0).getSerialNumber() + 1);
        List<Plant> plant = em.createNamedQuery(Plant.GET_ALL_PLANTS).getResultList();
        for(Plant p:plant) {
            if(p.getPid() == pid)
                plantDetail.setPlant(p);
        }
        List<PPUser> users = em.createNamedQuery(PPUser.GET_ALL_QUERY_NAME).getResultList();
        for(PPUser user:users)
        {
            if(user.getContact().equals(contact))
                plantDetail.setUser(user);
        }
        
        em.persist(plantDetail); 
    }
    
    @Override
    public List<PlantDetails> findPlantDetailsByPid(int pid) throws Exception {
        List<PlantDetails> plantDetails = em.createNamedQuery(PlantDetails.GET_ALL_QUERY_NAME).getResultList(); 
        
        for(PlantDetails pd: plantDetails){
            if(pd.getPlant().getPid() != pid)
                    plantDetails.remove(pd);        }
        return plantDetails;
    }
    
    @Override
    public List<PlantDetails> getAllPlantDetails() throws Exception {
        List<PlantDetails> plants = em.createNamedQuery(PlantDetails.GET_ALL_QUERY_NAME).getResultList();
        return plants;
        
    }

    
    @Override
    public void removePlantDetails(int serialNumber) throws Exception {
        PlantDetails plant = findPlantDetailsBySerialNumber(serialNumber);
        List<PlantDetails> plants = em.createNamedQuery(PlantDetails.GET_ALL_QUERY_NAME).getResultList();
        for(PlantDetails p:plants)
        {
            if(p.getSerialNumber() == serialNumber)
                em.remove(p);
        }
    }
    @Override
    public void updatePlantDetails(PlantDetails plantDetails) throws Exception {
        em.merge(plantDetails);
    }
    
    @Override
    public List<Plant> findPlantDetailByFilter(double height, double price) throws Exception {
        /*
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(PlantDetails.class);
        Root root = cq.from(PlantDetails.class);
        cq.where(
                cb.and(cb.like(root.get("height"), height),
                                        cb.like(root.get("price"),price)
                                        
                                        
                                )
                        )
        );
        cq.select(root); 
        return (List<Plant>) em.createQuery(cq).getResultList(); */
        return null;
    }

    @Override
    public PlantDetails findPlantDetailsBySerialNumber(int serialNumber) {
        PlantDetails pd = em.find(PlantDetails.class, serialNumber);
        return pd;
    }
    
    


    @Override
    public Plant findPlantBySerialNumber(int serialNumber) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
