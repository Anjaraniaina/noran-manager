package me.noran.manager.controller;


import me.noran.manager.config.CompanyConf;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class PopulateController {


    @ModelAttribute("companyConf")
    private CompanyConf populateCompanyConf(){
        return new CompanyConf();
    }
}