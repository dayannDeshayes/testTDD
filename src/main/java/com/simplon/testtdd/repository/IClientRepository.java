package com.simplon.testtdd.repository;

import com.simplon.testtdd.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClientRepository extends JpaRepository<Client, Integer> {

    Client findByEmail(String email);

    List<Client> findBySexe(Client.Sexe sexe);
}