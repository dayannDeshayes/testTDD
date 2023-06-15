package com.simplon.testtdd;

import com.simplon.testtdd.entity.Client;
import com.simplon.testtdd.service.IClientService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class ClientServiceTest {

    @Autowired
    private IClientService clientService;

    @Test
    public void testAddClient_noClient_invalid() {
        Assertions.assertThrows(Exception.class, () -> clientService.addClient(null));
    }

    @Test
    public void testAddClient_save_valid() throws Exception {
        Client client1 = clientService.addClient(createClient_beforeSave());
        Client client2 = createClient_afterSave();

        Assertions.assertEquals(client1.getId(), client2.getId());
        Assertions.assertEquals(client1.getEmail(), client2.getEmail());
        Assertions.assertEquals(client1.getNom(), client2.getNom());
        Assertions.assertEquals(client1.getPrenom(), client2.getPrenom());
        Assertions.assertEquals(client1.getNumeroTelephone(), client2.getNumeroTelephone());
        Assertions.assertEquals(client1.getDateNaissance(), client2.getDateNaissance());
        Assertions.assertEquals(client1.getSexe(), client2.getSexe());
        Assertions.assertEquals(client1.getIsActive(), client2.getIsActive());
    }

    @Test
    public void testAddClients_noClient_invalid() {
        Assertions.assertThrows(Exception.class, () -> clientService.addClients(null));
    }

    @Test
    public void testGetClientById() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> clientService.getClientById(1));
    }

    @Test
    public void testGetClientByEmail() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> clientService.getClientByEmail("test@gmail.com"));
    }

    @Test
    public void testGetClientsBySexe() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> clientService.getClientsBySexe(Client.Sexe.FEMME));
    }

    @Test
    public void testDeleteClient() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> clientService.deleteClient(1));
    }

    @Test
    public void testDisableClient() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> clientService.disableClient(1));
    }

    @Test
    public void testupdateClient() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> clientService.updateClient(new Client()));
    }

    public Client createClient_beforeSave() {
        Client client = new Client();
        client.setNom("test");
        client.setPrenom("test");
        client.setEmail("test@gmail.com");
        client.setSexe(Client.Sexe.FEMME);
        client.setDateNaissance(new Date("01/01/2000"));
        client.setIsActive(true);
        client.setNumeroTelephone("0606060606");
        return client;
    }

    public Client createClient_afterSave() {
        Client client = new Client();
        client.setId(1);
        client.setNom("test");
        client.setPrenom("test");
        client.setEmail("test@gmail.com");
        client.setSexe(Client.Sexe.FEMME);
        client.setDateNaissance(new Date("01/01/2000"));
        client.setIsActive(true);
        client.setNumeroTelephone("0606060606");
        return client;
    }

}
