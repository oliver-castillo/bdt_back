package com.app.bdt.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "talento")
public class Talento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "talento_sequence")
    @SequenceGenerator(name = "talento_sequence", sequenceName = "talento_sequence", allocationSize = 100)
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

    @Column(name = "date", updatable = false, columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fechaCreacion;

}