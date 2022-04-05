package com.bosonit.backend.persona;

import com.bosonit.backend.persona.domain.Persona;
import com.bosonit.backend.persona.infrastructure.controller.dto.input.PersonaInputDTO;
import com.bosonit.backend.persona.repository.PersonaRepositoryJPA;
import com.bosonit.backend.persona.service.PersonaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PersonaApp.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonaControllerTest {
    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonaService service;

    @Autowired
    private PersonaRepositoryJPA mockRepository;

    @BeforeAll
    public void init() {
        PersonaInputDTO personaInputDTO = crearPersona();
        Persona persona = new Persona();
        BeanUtils.copyProperties(personaInputDTO, persona);
        mockRepository.save(persona);
    }

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

    @Test
    void getPersona() throws Exception {
        this.mockMvc.perform(get("/persona/1"))
                .andExpect(status().isOk())
                .andReturn();
        verify(service, times(1)).getPersona(1);
    }

    @Test
    void addPersona() throws Exception {
        // Creamos persona
        PersonaInputDTO personaInputDTO = crearPersona();
        personaInputDTO.setUsuario("otrouser");

        // Convertimos a formato json e intentamos agregar
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

        MvcResult result = this.mockMvc.perform(post("/persona")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(personaInputDTO)))
                .andExpect(status().isCreated())
                .andReturn();

        // Recuperamos datos y comprobamos FIXME recuperacion
//        String contenido = result.getResponse().getContentAsString();
//        System.out.println("holaaaaa" + contenido);
//        PersonaOutputDTO p = gson.fromJson(contenido, PersonaOutputDTO.class);
//        Assertions.assertEquals(p.getUsuario(),personaInputDTO.getUsuario());
//        Assertions.assertEquals(p.getName(), personaInputDTO.getName());
    }

    @Test
    void getPersonas() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/personas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void actPersona() throws Exception {
        // Creamos persona
        PersonaInputDTO personaInputDTO = crearPersona();
        personaInputDTO.setName("Inmaids");

        // Convertimos a formato json e intentamos agregar
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

        MvcResult result = this.mockMvc.perform(put("/persona/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(personaInputDTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Recuperamos datos y comprobamos FIXME recuperacion
    }

    @Test
    void delPersona() throws Exception {
        MvcResult result = this.mockMvc.perform(delete("/persona/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
}
