package com.app.bdt.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "BT_TM_LISTA_USUARIO")
public class UserList implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_LISTA_USUARIO")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "ID_USUARIO")
  private User user;

  @OneToMany
  @JoinColumn(name = "ID_LISTA_USUARIO")
  @ToString.Exclude
  private List<UserTalentList> talentsList;

  @Column(name = "NO_LISTA_USUARIO")
  private String listName;

  @Column(name = "FE_CREACION", updatable = false)
  @CreationTimestamp
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private LocalDate creationDate;

}