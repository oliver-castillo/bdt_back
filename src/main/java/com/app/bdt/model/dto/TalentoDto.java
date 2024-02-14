package com.app.bdt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TalentoDto {

    private Long id;

    private String nombre;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private byte[] imagen;

    private String descripcion;

    private Double montoInicial;

    private Double montoFinal;

    private String nroCelular;

    private String linkLinkedin;

    private String linkGithub;
    /*
     * @NotBlank(message = "The first name is required")
     *
     * @Length(min = 3, message = "The first name must be at least 3 characters")
     * private String firstName;
     *
     * @NotBlank(message = "The last name is required")
     *
     * @Length(min = 3, message = "The last name must be at least 3 characters")
     * private String lastName;
     *
     * @NotBlank(message = "The email name is required")
     *
     * @Email
     * private String email;
     *
     * @JsonProperty(access = Access.WRITE_ONLY)
     *
     * @NotBlank(message = "The password name is required")
     *
     * @Length(min = 8, message = "The password name must be at least 8 characters")
     * private String password;
     */
}
