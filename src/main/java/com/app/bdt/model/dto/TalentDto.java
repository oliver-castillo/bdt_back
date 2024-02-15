package com.app.bdt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TalentDto {

    @NotBlank
    private Long id;

    @NotBlank(message = "El nombre es requerido")
    @Length(min = 3, message = "El nombre debe contener más de 3 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido paterno es requerido")
    @Length(min = 3, message = "El apellido paterno debe contener más de 3 caracteres")
    private String apellidoPaterno;

    @NotBlank(message = "El apellido materno es requerido")
    @Length(min = 3, message = "El apellido materno debe contener más de 3 caracteres")
    private String apellidoMaterno;

    @NotBlank(message = "La imagen es requerida")
    private String imagen;

    @NotBlank(message = "La descripción es requerida")
    @Length(min = 3, message = "La descripción debe contener más de 3 caracteres.")
    private String descripcion;

    @NotBlank(message = "El monto inicial es requerido")
    private Double montoInicial;

    @NotBlank(message = "El monto final es requerido")
    private Double montoFinal;

    @NotBlank(message = "El número de celular del talento es requerido")
    private String nroCelular;

    @NotBlank(message = "El link del LinkedIn del talento es requerido")
    private String linkLinkedin;

    @NotBlank(message = "El link de GitHub del talento es requerido")
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
