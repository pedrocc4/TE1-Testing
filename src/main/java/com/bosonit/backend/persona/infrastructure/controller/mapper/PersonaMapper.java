package com.bosonit.backend.persona.infrastructure.controller.mapper;

import com.bosonit.backend.persona.domain.Persona;
import com.bosonit.backend.persona.infrastructure.controller.dto.input.PersonaInputDTO;
import com.bosonit.backend.persona.infrastructure.controller.dto.output.PersonaOutputDTO;
import org.mapstruct.Mapper;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@Validated
public interface PersonaMapper {

    @Valid Persona toEntity(PersonaInputDTO personaDTO);

    PersonaOutputDTO toDTO(@Valid Persona persona);

    default List<PersonaOutputDTO> toDTOList(@Valid List<Persona> personas) {
        if (personas == null) {
            return new ArrayList<>();
        }
        return personas.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
