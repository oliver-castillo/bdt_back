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
@Table(name = "BT_TD_FEEDBACK_TALENTO")
public class Feedback implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_FEEDBACK")
  private Long id;

  /*@ManyToOne
  @JoinColumn(name = "ID_TALENTO")
  private Talent talent;*/

  @Column(name = "NU_ESTRELLAS")
  private Integer starsNumber;

  @Column(name = "DE_DESCRIPCION")
  private String description;

  /*@ManyToOne
  @JoinColumn(name = "ID_USUARIO")
  private User user;*/

}