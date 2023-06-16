package com.simplon.testtdd.entity.mapper;

import com.simplon.testtdd.entity.Client;
import com.simplon.testtdd.entity.dto.ClientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IClientMapper {
    Client toClient(ClientDto clientDto);

    ClientDto toClientDto(Client client);
}
