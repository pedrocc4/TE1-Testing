package com.bosonit.backend.persona.repository;

import com.bosonit.backend.persona.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepositoryJPA extends JpaRepository<Persona, Integer> {
    @Query("SELECT p FROM Persona p WHERE p.usuario = ?1")
    Optional<Persona> findByUsername(String username);
}
