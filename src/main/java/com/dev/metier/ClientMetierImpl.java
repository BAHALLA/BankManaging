package com.dev.metier;

import com.dev.dao.ClientRepository;
import com.dev.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientMetierImpl implements IClientMetier {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Optional<Client> getOne(Long id) {
       Optional<Client> cl = clientRepository.findById(id);
       if(cl ==null ) throw  new RuntimeException("No client founded");
       return cl;
    }

    @Override
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client modifier(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void delete(long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Page<Client> listClients(String nomClient, int page, int size) {
        System.out.println(nomClient);
        return clientRepository.listClient("%"+nomClient+"%", PageRequest.of(page,size));

    }
}
