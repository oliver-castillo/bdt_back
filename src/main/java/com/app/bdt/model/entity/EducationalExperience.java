package com.app.bdt.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

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
public class EducationalExperience implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EXPERIENCIA_EDUCATIVA")
    private Long id;

    @Column(name = "NO_INSTITUCION_EDUCATIVA")
    private String educationalInstitute;

    @Column(name = "NO_CARRERA")
    private String career;

    @Column(name = "NO_GRADO")
    private String degree;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "FE_INICIO")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "FE_FIN")
    private LocalDate endDate;

}