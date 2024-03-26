package com.app.bdt.model.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

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

  @Column(name = "FL_ACTUALIDAD")
  private boolean isCurrent;

  public LocalDate getEndDate() {
    if (isCurrent) {
      endDate = null;
    }
    return endDate;
  }

}