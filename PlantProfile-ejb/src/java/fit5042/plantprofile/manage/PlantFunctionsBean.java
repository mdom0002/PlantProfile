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
import java.util.ArrayList;
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
public class PlantFunctionsBean implements PlantFunctions{

    @PersistenceContext(unitName = "PlantProfile-ejbPU")
    private EntityManager em;
    
    @Override
    public void addPlant(Plant plant,String contact) throws Exception {
        List<Plant> plants =  em.createNamedQuery(Plant.GET_ALL_PLANTS).getResultList();                 
        plant.setPid(plants.get(0).getPid() + 1);
        List<PPUser> users = em.createNamedQuery(PPUser.GET_ALL_QUERY_NAME).getResultList();
        for(PPUser user:users)
        {
            if(user.getContact().equals(contact))
                plant.setUser(user);
        }
        em.persist(plant);
    }
    
    @Override
    public Plant findPlantByPid(int pid) throws Exception {
        Plant plant = em.find(Plant.class, pid);
        return plant;
    }
    
    @Override
    public List<PlantDetails> findPlantDetailsByPid(int pid) throws Exception {
        List<PlantDetails> plantDetails = em.createNamedQuery(PlantDetails.GET_PLANT).getResultList(); 
        //List<PlantDetails> plant = null; 
        //for(PlantDetails pd: plantDetails){
        //    if(pd.getPlant().getPid() != pid)
        //        plantDetails.remove(pd);       }
        return plantDetails;
    }

    @Override
    public List<Plant> getAllPlants() throws Exception {
        List<Plant> plants = em.createNamedQuery(Plant.GET_ALL_PLANTS).getResultList();        
        return plants;
        
    } 
    
    @Override
    public void removePlant(int pid) throws Exception {
        Plant plant = findPlantByPid(pid);
        List<Plant> plants = em.createNamedQuery(Plant.GET_ALL_PLANTS).getResultList();
        for(Plant p:plants)
        {
            if(p.getPid() == pid)
                em.remove(p);
        }
       /* List<PlantDetails> plantDetails = em.createNamedQuery(PlantDetails.GET_BY_PID).setParameter("pid", pid).getResultList();
        if(plant != null)
        {
            em.remove(plant);
        }*/
    }
    
    @Override
    public void updatePlant(Plant plant) throws Exception {
        em.merge(plant);
    }
    
    @Override
    public List<Plant> findPlantByFilter(String name, double temperature, String color) throws Exception {
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Plant.class);
        Root root = cq.from(Plant.class);
        cq.where(
                cb.and(cb.like(root.get("name"), "%" + name + "%"),
                                cb.and(cb.equal(root.get("temperature"), temperature),
                                        cb.like(root.get("color"), "%" + color + "%")
                                        
                                        
                                )
                        )
        );
        cq.select(root);
        return (List<Plant>) em.createQuery(cq).getResultList();
    }
    
     
    
    @Override
    public Plant findPlantBySerialNumber(int serialNumber) {
        PlantDetails pd = em.find(PlantDetails.class, serialNumber);
        return pd.getPlant();
    }
    
    @Override
    public List<Plant> searchPlantByHeight(double heightMin,double heightMax) throws Exception {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(PlantDetails.class);
        Root<PlantDetails> p = cq.from(PlantDetails.class);
        cq.select(p).where(cb.ge(p.get("height").as(Double.class), heightMin),cb.le(p.get("height").as(Double.class), heightMax));
        List<PlantDetails> lp = em.createQuery(cq).getResultList();
        ArrayList<Plant> plantList = new ArrayList<>();
        for(PlantDetails pd: lp)
        {
            plantList.add(pd.getPlant());
        }
        return plantList;
    }
    
    @Override
    public List<Plant> searchPlantByColor(String color) throws Exception {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Plant.class);
        Root p = cq.from(Plant.class);
        cq.select(p).where(cb.like(p.get("color"), "%" + color + "%"));
        List<Plant> plantList = em.createQuery(cq).getResultList();
        
        return plantList;
    }


  
}
