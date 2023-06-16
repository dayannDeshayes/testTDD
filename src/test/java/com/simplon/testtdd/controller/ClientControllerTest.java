package com.simplon.testtdd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplon.testtdd.entity.Client;
import com.simplon.testtdd.entity.dto.ClientDto;
import com.simplon.testtdd.service.ClientService;
import com.simplon.testtdd.service.IClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IClientService clientService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        ClientController clientController = new ClientController();
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    public void testGetAllClients() throws Exception {
        // Mocking service
        when(clientService.getAllClients()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/clients/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(clientService, times(1)).getAllClients();
    }

    @Test
    void testSaveClient() throws Exception {

        Client client = new Client();
        client.setEmail("test@gmail.com");
        client.setNom("test");
        client.setPrenom("test");
        client.setNumeroTelephone("0606060606");
        client.setDateNaissance(new Date("15/06/2023"));
        client.setSexe(Client.Sexe.FEMME);
        client.setIsActive(true);

        when(clientService.addClient(any(Client.class))).thenReturn(client);

        // Act & Assert
        mockMvc.perform(post("/client/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(client)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@gmail.com"))
                .andExpect(jsonPath("$.nom").value("test"))
                .andExpect(jsonPath("$.prenom").value("test"))
                .andExpect(jsonPath("$.numeroTelephone").value("0606060606"))
                .andExpect(jsonPath("$.dateNaissance").value("2023-06-15"))
                .andExpect(jsonPath("$.sexe").value("FEMME"))
                .andExpect(jsonPath("$.isActive").value(true));

        verify(clientService, times(1)).addClient(any(Client.class));
    }

}
