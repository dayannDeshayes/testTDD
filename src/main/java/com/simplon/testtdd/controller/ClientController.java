package com.simplon.testtdd.controller;

import com.simplon.testtdd.entity.Client;
import com.simplon.testtdd.entity.dto.ClientDto;
import com.simplon.testtdd.entity.mapper.ClientMapper;
import com.simplon.testtdd.service.ClientService;
import com.simplon.testtdd.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private IClientService clientService;

    private ClientMapper clientMapper;

    @PostMapping("/save")
    public Client saveClient(@RequestBody ClientDto clientDto) throws Exception {
        return this.clientService.addClient(clientMapper.toClient(clientDto));
    }

    @GetMapping("/clients")
    public List<ClientDto> getAllClients() {
        List<ClientDto> clientDtoList = null;
        List<Client> clientList = this.clientService.getAllClients();

        for (Client client : clientList) {
            clientDtoList.add(clientMapper.toClientDto(client));
        }
        return clientDtoList;
    }

    @GetMapping("/{id}")
    public ClientDto getClientById(@PathVariable Integer id) throws Exception {
        return clientMapper.toClientDto(this.clientService.getClientById(id));
    }

    @GetMapping("/{email}")
    public ClientDto getClientByEmail(@PathVariable String email) throws Exception {
        return clientMapper.toClientDto(this.clientService.getClientByEmail(email));
    }

    @GetMapping("/{sexe}")
    public List<ClientDto> getClientsBySexe(@PathVariable Client.Sexe sexe) throws Exception {
        List<ClientDto> clientDtoList = null;
        List<Client> clientList = this.clientService.getClientsBySexe(sexe);

        for (Client client : clientList) {
            clientDtoList.add(clientMapper.toClientDto(client));
        }
        return clientDtoList;
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Integer id) throws Exception {
        this.clientService.deleteClient(id);
    }

    @PutMapping("/update")
    public ClientDto updateClient(@RequestBody ClientDto clientDto) throws Exception {
        return clientMapper.toClientDto(this.clientService.updateClient(clientMapper.toClient(clientDto)));
    }

    @PutMapping("/disable/{id}")
    public ClientDto disableClient(@PathVariable Integer id) throws Exception {
        return clientMapper.toClientDto(this.clientService.disableClient(id));
    }

}
