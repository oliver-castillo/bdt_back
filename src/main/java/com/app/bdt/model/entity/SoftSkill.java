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
@Builder
@Table(name = "BT_TD_HABILIDAD_BLANDA")
public class SoftSkill implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_HABILIDAD_BLANDA")
  private Long id;

  @Column(name = "NO_HABILIDAD")
  private String skill;

}