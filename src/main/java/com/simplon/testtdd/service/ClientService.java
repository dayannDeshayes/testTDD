package com.simplon.testtdd.service;

import com.simplon.testtdd.entity.Client;
import com.simplon.testtdd.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService implements IClientService {

    @Autowired
    private IClientRepository clientRepository;

    public Client addClient(Client client) throws Exception {
        if (client == null) {
            throw new Exception("Client cannot be null");
        }

        return this.clientRepository.save(client);
    }

    public List<Client> addClients(List<Client> clients) throws Exception {
        if (clients.size() > 0) {
            throw new Exception("Client cannot be void");
        }

        return this.clientRepository.saveAll(clients);
    }

    public List<Client> getAllClients() {
        return this.clientRepository.findAll();
    }

    public Client getClientById(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("id cannot be null");
        }
        return this.clientRepository.findById(id).get();

    }

    public Client getClientByEmail(String email) throws Exception {
        if (email == null) {
            throw new Exception("email cannot be null");
        }
        return this.clientRepository.findByEmail(email);
    }

    public List<Client> getClientsBySexe(Client.Sexe sexe) throws Exception {
        if (sexe == null) {
            throw new Exception("Sexe cannot be null");
        }
        return this.clientRepository.findBySexe(sexe);
    }

    public void deleteClient(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("id cannot be null");
        }
        this.clientRepository.deleteById(id);
    }

    public Client disableClient(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("id cannot be null");
        }

        Client client = this.clientRepository.findById(id).get();
        client.setIsActive(false);
        return updateClient(client);
    }

    public Client updateClient(Client client) throws Exception {
        if (client == null) {
            throw new Exception("no client");
        }
        return this.clientRepository.save(client);
    }
}
