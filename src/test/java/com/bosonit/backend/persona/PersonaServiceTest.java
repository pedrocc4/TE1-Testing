package com.bosonit.backend.persona;

import com.bosonit.backend.persona.infrastructure.controller.dto.input.PersonaInputDTO;
import com.bosonit.backend.persona.infrastructure.controller.dto.output.PersonaOutputDTO;
import com.bosonit.backend.persona.service.PersonaService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

@SpringBootTest(classes =
        com.bosonit.backend.persona.PersonaApp.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonaServiceTest {

    @Autowired
    private PersonaService service;

    private PersonaInputDTO crearPersona() {
        // Definimos una persona para testear
        PersonaInputDTO persona = new PersonaInputDTO();
        persona.setUsuario("Username");
        persona.setName("Eduardo");
        persona.setActive(true);
        persona.setCity("Jaen");
        persona.setPersonal_email("email@email.com");
        persona.setCompany_email("email@email.com");
        persona.setCreated_date(Date.from
                (LocalDate.parse("2021-11-11")
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant()));
        persona.setPassword("maxsecurity");

        return persona;
    }

    // CRUD de persona

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void addPersonaTest() {
        PersonaInputDTO persona = crearPersona();

        // Agregamos y comprobamos su persistencia
        PersonaOutputDTO output = service.addPersona(persona);
        Assertions.assertThat(service.getPersona(output.getId())
                .getUsuario()).isEqualTo(persona.getUsuario());
        Assertions.assertThat(service.getPersonas().size()).isEqualTo(1);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void getPersonaTest() {
        PersonaInputDTO persona = crearPersona();

        // Agregamos y comprobamos su persistencia
        PersonaOutputDTO output = service.addPersona(persona);
        Assertions.assertThat(service.getPersona(output.getId())
                .getUsuario()).isEqualTo(persona.getUsuario());
        Assertions.assertThat(service.getPersonas().size()).isEqualTo(1);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void getPersonasTest() {
        PersonaInputDTO persona = crearPersona();

        // Agregamos y comprobamos su persistencia
        PersonaOutputDTO output = service.addPersona(persona);
        Assertions.assertThat(service.getPersona(output.getId())
                .getUsuario()).isEqualTo(persona.getUsuario());
        Assertions.assertThat(service.getPersonas().size()).isEqualTo(1);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void getPersonaByUserTest() {
        PersonaInputDTO persona = crearPersona();

        // Agregamos y comprobamos su persistencia
        PersonaOutputDTO output = service.addPersona(persona);
        Assertions.assertThat(service.getPersonaByUser(output.getUsuario())
                .getUsuario()).isEqualTo(persona.getUsuario());
        Assertions.assertThat(service.getPersonas().size()).isEqualTo(1);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void actPersonaTest() {
        PersonaInputDTO persona = crearPersona();

        // Agregamos y comprobamos su persistencia
        PersonaOutputDTO output = service.addPersona(persona);
        Assertions.assertThat(service.getPersona(output.getId())
                .getUsuario()).isEqualTo(persona.getUsuario());
        Assertions.assertThat(service.getPersonas().size()).isEqualTo(1);

        // Actualizamos
        BeanUtils.copyProperties(output, persona);
        persona.setName("Santiago");
        service.actPersona(output.getId(), persona);

        // Comprobamos su actualizado
        Assertions.assertThat(service.getPersona(output.getId())
                .getUsuario()).isEqualTo(persona.getUsuario());
        Assertions.assertThat(service.getPersona(output.getId())
                .getName()).isEqualTo("Santiago");
        Assertions.assertThat(service.getPersonas().size()).isEqualTo(1);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void delPersonaTest() {
        PersonaInputDTO persona = crearPersona();

        // Agregamos y comprobamos su persistencia
        PersonaOutputDTO output = service.addPersona(persona);
        Assertions.assertThat(service.getPersona(output.getId())
                .getUsuario()).isEqualTo(persona.getUsuario());
        Assertions.assertThat(service.getPersonas().size()).isEqualTo(1);

        // Intentamos eliminar
        service.delPersona(output.getId());
        Assertions.assertThat(service.getPersonas().size()).isEqualTo(0);
    }
}
