/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.entities;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Medo
 */
@Entity
@NamedQueries({@NamedQuery(name = PPUser.GET_ALL_QUERY_NAME, query = "SELECT s FROM PPUser s order by s.userId desc"),
    @NamedQuery(name = PPUser.GET_BY_TYPE, query = "SELECT s FROM PPUser s where s.userType =:type order by s.userId desc"),
               @NamedQuery(name = PPUser.FIND_BY_CONTACT, query = "SELECT s FROM PPUser s WHERE s.contact = :contact and s.password=:password"),
               @NamedQuery(name = PPUser.FIND_BY_ID, query = "SELECT s FROM PPUser s WHERE s.userId = :sid")})

public class PPUser implements Serializable{
    public static final String GET_ALL_QUERY_NAME = "User.getAll";
    public static final String FIND_BY_CONTACT = "User.findUserByContact";
    public static final String FIND_BY_ID = "User.findUserById";  
    public static final String GET_BY_TYPE = "User.findByType";
   
   private int userId;
   private String firstName;
   private String lastName;
   private Address address;
   private String contact;
   private String password;
   private String email;
   private String userType;
   private Set<Plant> plant;
   private Set<PlantDetails> plantDetails;

   public PPUser()
   { 
   
       this.userId = 0;
        this.firstName = "";
        this.lastName = "";
        this.contact = "";
        this.password = "";
        this.email = "";
        this.address = new Address();
   }
    public PPUser(int userId, String firstName, String lastName, Address address, String contact, String password, String email) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.contact = contact;
        this.password = password;
        this.email = email;
    }
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @OneToMany(targetEntity = Plant.class,mappedBy = "user")
    public Set<Plant> getPlant() {
        return plant;
    }

    public void setPlant(Set<Plant> plant) {
        this.plant = plant;
    }

    @OneToMany(targetEntity = PlantDetails.class,mappedBy = "user")
    public Set<PlantDetails> getPlantDetails() {
        return plantDetails;
    }

    public void setPlantDetails(Set<PlantDetails> plantDetails) {
        this.plantDetails = plantDetails;
    }


   @Basic(optional = false)
   @Size(min = 0, max = 20)
   @Pattern(regexp = "[A-Za-z]+", message = "Only characters allowed")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Embedded
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Column(unique = true)
    @Size(min = 0, max = 10)
    @NotNull(message = "Please enter the contact")
    @Pattern(regexp = "[0]\\d{9}" , message = "Please enter a valid contact starting with 0 and have 10 digits in total eg:0498765432")
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Size(min = 0, max = 300)
    @NotNull(message = "Please enter the password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String inputPassword) {
        try{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(inputPassword.getBytes("UTF-8"));
        String convertedPassword = DatatypeConverter.printHexBinary(hash);
        this.password = convertedPassword;
        }catch(Exception e)
        {}
        
    }

    @Basic(optional = false)
    @Size(min = 0, max = 30)
    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z]+", message = "Please enter a valid email eg:customer@gmail.com")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
   
    
   
    
}
