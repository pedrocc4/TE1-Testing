package com.bosonit.backend.persona.service;

import com.bosonit.backend.persona.domain.Persona;
import com.bosonit.backend.persona.infrastructure.controller.dto.input.PersonaInputDTO;
import com.bosonit.backend.persona.infrastructure.controller.dto.output.PersonaOutputDTO;
import com.bosonit.backend.persona.infrastructure.controller.mapper.PersonaMapper;
import com.bosonit.backend.persona.infrastructure.exceptions.PersonaNoEncontrada;
import com.bosonit.backend.persona.infrastructure.exceptions.UnprocesableException;
import com.bosonit.backend.persona.repository.PersonaRepositoryJPA;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepositoryJPA repository;

    @Autowired
    private PersonaMapper mapper;

    @Override
    public PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) throws UnprocesableException {
            return mapper.toDTO(repository.save(mapper.toEntity(personaInputDTO)));
    }

    @Override
    public PersonaOutputDTO getPersona(Integer id) throws PersonaNoEncontrada {
        return mapper.toDTO(repository
                .findById(id)
                .orElseThrow(() -> new PersonaNoEncontrada
                        ("Persona con id: " + id + ", no encontrado")));
    }

    @Override
    public PersonaOutputDTO getPersonaByUser(String username) throws PersonaNoEncontrada {
        return mapper.toDTO(repository
                .findByUsername(username)
                .orElseThrow(() -> new PersonaNoEncontrada
                        ("Usuario: " + username + ", no encontrado")));
    }

    @Override
    public List<PersonaOutputDTO> getPersonas() {
        return mapper.toDTOList(repository.findAll());
    }

    @Override
    public void actPersona(int id, PersonaInputDTO personaInputDTO)
            throws PersonaNoEncontrada, UnprocesableException {
        Persona persona =
                repository
                        .findById(id)
                        .orElseThrow(() ->
                                new PersonaNoEncontrada(
                                        "Persona con id: " + id + ", no encontrado"));

        // Asignacion de nuevos atributos
        BeanUtils.copyProperties(personaInputDTO,persona);
        persona.setId(id);
        mapper.toDTO(repository.save(persona));
    }

    @Override
    public void delPersona(int id) throws PersonaNoEncontrada {
        repository.delete((repository
                .findById(id)
                .orElseThrow(() -> new PersonaNoEncontrada
                        ("Persona con id: " + id + ", no encontrado"))));
    }
}
