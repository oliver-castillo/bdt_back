package com.app.bdt.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "BT_TD_ARCHIVOS")
public class File implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_ARCHIVO")
  private Long id;

  @Column(name = "NO_ARCHIVO")
  private String fileName;

  @Column(name = "FL_TIPO_ARCHIVO")
  private String fileType;

  @Lob
  @Column(name = "AR_ARCHIVO")
  private byte[] fileInBytes;

}