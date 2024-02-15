package com.app.bdt.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "BT_TD_EXPERIENCIA_EDUCATIVA")
public class ExperienciaEducativa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "experienciaeducativa_sequence")
    @SequenceGenerator(name = "experienciaeducativa_sequence", sequenceName = "experienciaeducativa_sequence", allocationSize = 100)
    @Column(name = "ID_EXPERIENCIA_EDUCATIVA")
    private Long id;

    @Column(name = "NO_INSTITUCION_EDUCATIVA")
    private String institucionEducativa;

    @Column(name = "NO_CARRERA")
    private String carrera;

    @Column(name = "NO_GRADO")
    private String grado;

    @Column(name = "FE_INICIO")
    private LocalDate fechaInicio;

    @Column(name = "FE_FIN")
    private LocalDate fechaFin;

    @ManyToOne
    @JoinColumn(name = "ID_TALENTO")
    private Talent talent;

}