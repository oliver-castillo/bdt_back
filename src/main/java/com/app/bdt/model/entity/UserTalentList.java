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
@Table(name = "BT_TX_LISTA_USUARIO_TALENTO")
public class UserTalentList implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_LISTA_USUARIO_DETALLE")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "ID_LISTA_USUARIO")
  private UserList userList;

  /*@ManyToOne
  @JoinColumn(name = "ID_TALENTO")
  private Talent talent;*/

}