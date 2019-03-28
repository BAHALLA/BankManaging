package com.dev.metier;

import com.dev.entities.Compte;
import com.dev.entities.Operations;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface IBanqueMetier {
    public Optional<Compte> consulterCompte(String codeCompte);
    public void verser(String codeCompte,double montant);
    public void retirer(String codeCompte,double montant);
    public void virement(String codeCompte1,String codeCompte2,double montant);
    public Page<Operations> listOperations(String codePage,int page,int size);
}
