package com.simplon.testtdd.entity.mapper;

import com.simplon.testtdd.entity.Client;
import com.simplon.testtdd.entity.dto.ClientDto;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ClientMapper {
    public Client toClient(ClientDto clientDto) {
        Client client = new Client();

        client.setEmail(clientDto.getEmail());
        client.setNom(clientDto.getNom());
        client.setPrenom(clientDto.getPrenom());
        client.setNumeroTelephone(clientDto.getNumeroTelephone());

        // Convert String to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date dateNaissance = new Date(LocalDate.parse(clientDto.getDateNaissance(), formatter).toEpochDay());
        client.setDateNaissance(dateNaissance);

        // Convert String to Enum
        client.setSexe(Client.Sexe.valueOf(clientDto.getSexe().toUpperCase()));

        client.setIsActive(clientDto.getIsActive());

        return client;
    }

    public ClientDto toClientDto(Client client) {
        ClientDto clientDto = new ClientDto();

        clientDto.setEmail(client.getEmail());
        clientDto.setNom(client.getNom());
        clientDto.setPrenom(client.getPrenom());
        clientDto.setNumeroTelephone(client.getNumeroTelephone());

        // Convert LocalDate to String
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateNaissance = client.getDateNaissance().toString();
        clientDto.setDateNaissance(dateNaissance);

        // Convert Enum to String
        clientDto.setSexe(client.getSexe().toString());

        clientDto.setIsActive(client.getIsActive());

        return clientDto;
    }

}
