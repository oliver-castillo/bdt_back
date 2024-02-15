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
@Table(name = "BT_TD_EXPERIENCIA_LABORAL")
public class ExperienciaLaboral implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "experiencialaboral_sequence")
    @SequenceGenerator(name = "experiencialaboral_sequence", sequenceName = "experiencialaboral_sequence", allocationSize = 100)
    @Column(name = "ID_EXPERIENCIA_LABORAL")
    private Long id;

    @Column(name = "NO_EXPERIENCIA_LABORAL")
    private String nomExperienciaLaboral;

    @Column(name = "NO_PUESTO")
    private String puesto;

    @Column(name = "FE_INICIO")
    private LocalDate fechaInicio;

    @Column(name = "FE_FIN")
    private LocalDate fechaFin;

    @ManyToOne
    @JoinColumn(name = "ID_TALENTO")
    private Talent talent;

}