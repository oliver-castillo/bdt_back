package com.app.bdt.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "BT_TD_HABILIDAD_TECNICA")
public class TechnicalSkill implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_HABILIDAD_TECNICA")
  private Long id;

  @Column(name = "NO_HABILIDAD")
  private String skill;

  @Column(name = "NU_ANIOS")
  private Integer years;

}