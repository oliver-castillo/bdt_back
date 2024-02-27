package com.app.bdt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BT_TX_MASTER_TALENTO_IDIOMA")
public class Language implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_MASTER_TALENTO_IDIOMA")
  private Long id;

  @Column(name = "ID_IDIOMA")
  private int languageId;

  @Column(name = "ID_NIVEL")
  private int levelId;

  @Column(name = "NU_ESTRELLAS")
  private int numberOfStars;

}
