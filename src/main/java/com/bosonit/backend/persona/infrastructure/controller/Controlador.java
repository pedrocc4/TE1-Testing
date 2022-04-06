package com.bosonit.backend.persona.infrastructure.controller;

import com.bosonit.backend.persona.infrastructure.controller.dto.input.PersonaInputDTO;
import com.bosonit.backend.persona.infrastructure.controller.dto.output.PersonaOutputDTO;
import com.bosonit.backend.persona.infrastructure.exceptions.PersonaNoEncontrada;
import com.bosonit.backend.persona.infrastructure.exceptions.UnprocesableException;
import com.bosonit.backend.persona.service.PersonaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
public class Controlador {

    @Autowired
    private PersonaService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("persona")
    public ResponseEntity<PersonaOutputDTO> addPersona(@RequestBody PersonaInputDTO personaInputDTO) {
        log.info("Intentando agregar: " + personaInputDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addPersona(personaInputDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("persona/{id}")
    public ResponseEntity<PersonaOutputDTO> getPersona(@PathVariable int id) {
        log.info("Intentando buscar persona con id: " + id);
        return ResponseEntity.ok().body(service.getPersona(id));
    }

    @GetMapping("personas")
    public List<PersonaOutputDTO> getPersonas() {
        log.info("Mostrando todas las personas");
        return service.getPersonas();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("persona")
    public ResponseEntity<PersonaOutputDTO> getPersona(@RequestParam String username) {
        log.info("Intentando buscar persona con nombre: " + username);
        return ResponseEntity.ok().body(service.getPersonaByUser(username));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("persona/{id}")
    public ResponseEntity<Void> actPersona(@PathVariable int id, @RequestBody PersonaInputDTO personaInputDTO) {
        log.info("Intentando actualizar: " + personaInputDTO);
        service.actPersona(id, personaInputDTO);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("persona/{id}")
    public ResponseEntity<Void> delPersona(@PathVariable int id) {
        log.info("Intentando borrar persona con id: " + id);
        service.delPersona(id);
        return ResponseEntity.ok().build();
    }
}
