package com.bosonit.backend.persona.service;

import com.bosonit.backend.persona.infrastructure.controller.dto.input.PersonaInputDTO;
import com.bosonit.backend.persona.infrastructure.controller.dto.output.PersonaOutputDTO;

import java.util.List;

public interface PersonaService {
    PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO);

    PersonaOutputDTO getPersona(Integer id);

    List<PersonaOutputDTO> getPersonas();

    PersonaOutputDTO getPersonaByUser(String username);

    void actPersona(int id, PersonaInputDTO personaInputDTO);

    void delPersona(int id);
}
