/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.plantprofile.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Medo
 */
@Named(value = "titleController")
@RequestScoped
public class TitleController {
    private String pageTitle;
    
    public TitleController() {
        // Set the page title 
        pageTitle = "Plant Profile";
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }
}
