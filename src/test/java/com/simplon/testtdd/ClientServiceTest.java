package com.simplon.testtdd;

import com.simplon.testtdd.entity.Client;
import com.simplon.testtdd.repository.IClientRepository;
import com.simplon.testtdd.service.IClientService;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("application-test.properties")
public class ClientServiceTest {

    @Autowired
    private IClientService clientService;

    @MockBean
    private IClientRepository clientRepository;


    @Test
    public void testAddClient_noClient_invalid() {
        Assertions.assertThrows(Exception.class, () -> clientService.addClient(null));
    }

    @Test
    public void addClient_test() throws Exception {
        Client client = new Client();
        when(clientRepository.save(ArgumentMatchers.any(Client.class))).thenReturn(client);

        Client created = clientService.addClient(client);

        assertEquals(client, created);
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    public void getAllClients_test() {
        Client client1 = new Client();
        Client client2 = new Client();
        when(clientRepository.findAll()).thenReturn(Arrays.asList(client1, client2));

        List<Client> clients = clientService.getAllClients();

        assertEquals(2, clients.size());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    public void testAddClients_noClient_invalid() {
        Assertions.assertThrows(Exception.class, () -> clientService.addClients(null));
    }

    @Test
    public void testGetClientById() {
        Assertions.assertThrows(Exception.class, () -> clientService.getClientById(null));
    }

    @Test
    public void getClientById_test() throws Exception {
        Client client = new Client();
        when(clientRepository.findById(any(Integer.class))).thenReturn(Optional.of(client));

        Client found = clientService.getClientById(1);

        assertEquals(client, found);
        verify(clientRepository, times(1)).findById(1);
    }

    @Test
    public void testGetClientByEmail() {
        Assertions.assertThrows(Exception.class, () -> clientService.getClientByEmail(null));
    }

    @Test
    public void getClientByEmail_test() throws Exception {
        Client client = new Client();
        when(clientRepository.findByEmail(any(String.class))).thenReturn(client);

        Client found = clientService.getClientByEmail("test@example.com");

        assertEquals(client, found);
        verify(clientRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    public void testGetClientsBySexe() {
        Assertions.assertThrows(Exception.class, () -> clientService.getClientsBySexe(null));
    }

    @Test
    public void getClientsBySexe_test() throws Exception {
        Client client1 = new Client();
        Client client2 = new Client();
        when(clientRepository.findBySexe(any(Client.Sexe.class))).thenReturn(Arrays.asList(client1, client2));

        List<Client> clients = clientService.getClientsBySexe(Client.Sexe.FEMME);

        assertEquals(2, clients.size());
        verify(clientRepository, times(1)).findBySexe(Client.Sexe.FEMME);
    }

    @Test
    public void testDeleteClient() {
        Assertions.assertThrows(Exception.class, () -> clientService.deleteClient(null));
    }

    @Test
    public void deleteClient_test() throws Exception {
        doNothing().when(clientRepository).deleteById(any(Integer.class));

        clientService.deleteClient(1);

        verify(clientRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDisableClient() {
        Assertions.assertThrows(Exception.class, () -> clientService.disableClient(null));
    }

    @Test
    public void disableClient_test() throws Exception {
        Client client = new Client();
        client.setIsActive(true);
        when(clientRepository.findById(any(Integer.class))).thenReturn(Optional.of(client));
        when(clientRepository.save(any(Client.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Client disabled = clientService.disableClient(1);

        Assertions.assertFalse(disabled.getIsActive());
        verify(clientRepository, times(1)).save(any(Client.class));
    }


    @Test
    public void testupdateClient() {
        Assertions.assertThrows(Exception.class, () -> clientService.updateClient(null));
    }


    @Test
    public void updateClient_test() throws Exception {
        Client client = new Client();
        when(clientRepository.save(any(Client.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Client updated = clientService.updateClient(client);

        assertEquals(client, updated);
        verify(clientRepository, times(1)).save(client);
    }
}
