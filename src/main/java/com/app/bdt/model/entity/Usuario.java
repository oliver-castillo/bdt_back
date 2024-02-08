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
@Table(name = "BT_TM_USUARIO")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_sequence")
    @SequenceGenerator(name = "usuario_sequence", sequenceName = "usuario_sequence", allocationSize = 100)
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "NO_NOMBRE")
    private String nombre;

    @Column(name = "AP_APELLIDO_PATERNO")
    private String apellidoPaterno;

    @Column(name = "AP_APELLIDO_MATERNO")
    private String apellidoMaterno;

    @Column(name = "IM_IMAGEN")
    private byte[] imagen;

    @Column(name = "US_USUARIO")
    private String username;

    @Column(name = "PW_PASSWORD")
    private String password;

}