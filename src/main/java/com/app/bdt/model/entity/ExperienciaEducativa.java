package com.app.bdt.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
@Table(name = "experienciaeducativa")
public class ExperienciaEducativa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "experienciaeducativa_sequence")
    @SequenceGenerator(name = "experienciaeducativa_sequence", sequenceName = "experienciaeducativa_sequence", allocationSize = 100)
    private Long id;

    private String noInstitucionEducativa;

    private String carrera;

    private String grado;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    private Talento talento;

}