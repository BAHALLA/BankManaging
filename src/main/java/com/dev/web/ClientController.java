package com.dev.web;

import com.dev.entities.Client;
import com.dev.metier.IClientMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ClientController {

    @Autowired
    private IClientMetier iClientMetier;

    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public String clients() {
        return "clients";
    }

    @RequestMapping(value = "/chercherClient",method = RequestMethod.GET)
    public String chercherClient(Model model, String nomClient,
                                 @RequestParam(name = "page",defaultValue = "0") int page,
                                  @RequestParam(name = "size",defaultValue = "5")int size  ){
        model.addAttribute("nomClient",nomClient);
        try {
            Page<Client> cl = iClientMetier.listClients(nomClient,page,size);
            model.addAttribute("clients",cl.getContent());
            System.out.println(cl.getTotalPages());
            int [] pages =new  int[cl.getTotalPages()];
            model.addAttribute("pages",pages);
        }catch (Exception e){
            model.addAttribute("exception",e);
        }
        return "clients";
    }

    @RequestMapping(value = "/deleteClient", method = RequestMethod.GET)
    public String deleteClient(long codeClient,String nomClient) {
        iClientMetier.delete(codeClient);
        return "redirect:/chercherClient?nomClient="+nomClient;
    }
}
