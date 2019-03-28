package com.dev.metier;

import com.dev.dao.CompteRepository;
import com.dev.dao.OperationRepository;
import com.dev.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class BanqueMetierImpl implements IBanqueMetier {

    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;

    @Override
    public Optional<Compte> consulterCompte(String codeCompte) {
        Optional<Compte> cp = compteRepository.findById(codeCompte);
        if(cp == null) throw  new RuntimeException("compte introuvalbe");
        return cp;
    }

    @Override
    public void verser(String codeCompte, double montant) {
        Optional<Compte> cp = consulterCompte(codeCompte);
        Compte c =cp.get();
        Viressement v =new Viressement(new Date(),montant,c);
        operationRepository.save(v);
        c.setSolde(c.getSolde()+montant);
        compteRepository.save(c);
    }

    @Override
    public void retirer(String codeCompte, double montant) {
        Optional<Compte> c = consulterCompte(codeCompte);
        Compte cp = c.get();
            double facilities =0;
        if(cp instanceof CompteCourant){
            facilities = ((CompteCourant) cp).getDecouvert();
        }
        if(cp.getSolde() +facilities <montant)
            throw new RuntimeException("Sode Insuffisant");

        Retrait r =new Retrait(new Date(),montant,cp);
        operationRepository.save(r);
        cp.setSolde(cp.getSolde() - montant);
        compteRepository.save(cp);
    }

    @Override
    public void virement(String codeCompte1, String codeCompte2, double montant) {

        if(codeCompte1.equals(codeCompte2))
            throw new RuntimeException("operation Impossible : virement sur le meme compte");
        retirer(codeCompte1,montant);
        verser(codeCompte2,montant);
    }

    @Override
    public Page<Operations> listOperations(String codePage, int page, int size) {
        return operationRepository.listOperations(codePage,PageRequest.of(page,size));
    }
}
