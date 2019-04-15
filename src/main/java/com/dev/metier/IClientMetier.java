package com.dev.metier;

import com.dev.entities.Client;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface IClientMetier {

    public Optional<Client> getOne(Long id);
    public Client addClient(Client client);
    public Client modifier(Client client);
    public void delete(long id);
    public Page<Client> listClients(String nomClient,int page, int size);
}
