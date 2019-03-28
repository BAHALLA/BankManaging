package com.dev.web;

import com.dev.entities.Compte;
import com.dev.entities.Operations;
import com.dev.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class BanqueController {

    @Autowired
    private IBanqueMetier iBanqueMetier;

    @RequestMapping(value = "/")
    public String home() {
        return "redirect:/operations";
    }
    @RequestMapping(value = "/operations", method = RequestMethod.GET)
    public String index() {
        return "comptes";
    }

    @RequestMapping(value = "/consulterCompte", method = RequestMethod.GET)
    public String consulter(Model model, String code,
                            @RequestParam(name = "page", defaultValue = "0") int page
                           ) {
        model.addAttribute("codeCompte",code);
        try {
            Optional<Compte> cp = iBanqueMetier.consulterCompte(code);
            Page<Operations> operations = iBanqueMetier.listOperations(code,page,5);
            model.addAttribute("operations",operations.getContent());
            model.addAttribute("compte",cp.get());
            int [] pages =new  int[operations.getTotalPages()];
            model.addAttribute("pages",pages);
        }catch (Exception e){
            model.addAttribute("exception",e);
        }
        return "comptes";
    }

    @RequestMapping(value = "/saveOperation",method = RequestMethod.POST)
    public String saveOperation(Model model,String typeOperation,
                                double montant,String codeCompte,
                                String codeCompte2){
        try {
            if(typeOperation.equals("VER")){
                iBanqueMetier.verser(codeCompte,montant);
            }
            else if(typeOperation.equals("RET")){
                iBanqueMetier.retirer(codeCompte,montant);
            } else if(typeOperation.equals("VIR")){
                iBanqueMetier.virement(codeCompte,codeCompte2,montant);
            }
        }catch (Exception e){
            model.addAttribute("error",e);
            return "redirect:/consulterCompte?code="+codeCompte+"&error="+e.getMessage();
        }

        return "redirect:/consulterCompte?code="+codeCompte;
    }
}
