package com.bosonit.backend.persona.infrastructure.controller.dto.input;

import lombok.Data;

import java.util.Date;

@Data
public class PersonaInputDTO {
    private Integer id;
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;
}
