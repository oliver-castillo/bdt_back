package com.app.bdt.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@Table(name = "experiencialaboral")
public class ExperienciaLaboral implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "experiencialaboral_sequence")
    @SequenceGenerator(name = "experiencialaboral_sequence", sequenceName = "experiencialaboral_sequence", allocationSize = 100)
    private Long id;

    private String noExperienciaLaboral;

    private String puesto;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

    private Talento talento;

}