package com.simplon.testtdd.controller;

import com.simplon.testtdd.entity.Client;
import com.simplon.testtdd.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @PostMapping("/save")
    public Client saveClient(@RequestBody Client client) throws Exception {
        return this.clientService.addClient(client);
    }

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return this.clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Integer id) throws Exception {
        return this.clientService.getClientById(id);
    }

    @GetMapping("/{email}")
    public Client getClientByEmail(@PathVariable String email) throws Exception {
        return this.clientService.getClientByEmail(email);
    }

    @GetMapping("/{sexe}")
    public List<Client> getClientsBySexe(@PathVariable Client.Sexe sexe) throws Exception {
        return this.clientService.getClientsBySexe(sexe);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Integer id) throws Exception {
        this.clientService.deleteClient(id);
    }

    @PutMapping("/update")
    public Client updateClient(@RequestBody Client client) throws Exception {
        return this.clientService.updateClient(client);
    }

    @PutMapping("/disable/{id}")
    public Client disableClient(@PathVariable Integer id) throws Exception {
        return this.clientService.disableClient(id);
    }

}
