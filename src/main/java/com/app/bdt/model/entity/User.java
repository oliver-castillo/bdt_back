package com.app.bdt.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "BT_TM_USUARIO")
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_USUARIO")
  private Long id;

  @Column(name = "NO_NOMBRE")
  private String name;

  @Column(name = "AP_APELLIDO_PATERNO")
  private String paternalSurname;

  @Column(name = "AP_APELLIDO_MATERNO")
  private String maternalSurname;

  @Column(name = "IM_IMAGEN")
  private byte[] image;

  @Column(name = "US_USUARIO")
  private String username;

  @Column(name = "PW_PASSWORD")
  private String password;

}