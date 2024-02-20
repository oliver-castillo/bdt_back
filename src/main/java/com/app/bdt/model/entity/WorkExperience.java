package com.app.bdt.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "BT_TD_EXPERIENCIA_LABORAL")
public class WorkExperience implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EXPERIENCIA_LABORAL")
    private Long id;

    @Column(name = "NO_EXPERIENCIA_LABORAL")
    private String workExperienceName;

    @Column(name = "NO_PUESTO")
    private String position;

    @Column(name = "FE_INICIO")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "FE_FIN")
    private LocalDate endDate;


}