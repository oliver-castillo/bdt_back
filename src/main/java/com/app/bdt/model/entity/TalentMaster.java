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
@Table(name = "BT_TX_MASTER_TALENTO")
public class TalentMaster implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_MASTER_TALENTO")
  private Long id_master_talento;

  @ManyToOne
  @JoinColumn(name = "ID_TALENTO")
  private Talent talent;

  @Column(name = "ID_MASTER")
  private Integer idMaster;

  @Column(name = "ID")
  private Integer id;
}