package com.app.bdt.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
@Table(name = "BT_TM_MASTER")
public class Master implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "master_sequence")
    @SequenceGenerator(name = "master_sequence", sequenceName = "master_sequence", allocationSize = 100)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ID_MASTER")
    private Integer idMaster;

    @Column(name = "DE_DESCRIPCION")
    private String descripcion;

    @Column(name = "STRING_UNO")
    private String strUno;

    @Column(name = "STRING_DOS")
    private String strDos;

    @Column(name = "STRING_TRES")
    private String strTres;

    @Column(name = "ID_UNO")
    private Integer intUno;

    @Column(name = "ID_DOS")
    private Integer intDos;

    @Column(name = "ID_TRES")
    private Integer intTres;

}