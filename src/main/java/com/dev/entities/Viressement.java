package com.dev.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("V")
public class Viressement extends Operations {

    public Viressement() {
    }

    public Viressement(Date dateOperation, double montant, Compte compte) {
        super(dateOperation, montant, compte);
    }

}
