package com.app.bdt.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "BT_TM_TALENTO")
public class Talento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BET_SEQ_TALENTO")
    @SequenceGenerator(name = "BET_SEQ_TALENTO", sequenceName = "BET_SEQ_TALENTO", allocationSize = 100)
    @Column(name = "ID_TALENTO")
    private Long id;

    @Column(name = "NO_NOMBRE")
    private String nombre;

    @Column(name = "AP_APELLIDO_PATERNO")
    private String apellidoPaterno;

    @Column(name = "AP_APELLIDO_MATERNO")
    private String apellidoMaterno;

    @Column(name = "IM_IMAGEN")
    private byte[] imagen;

    @Column(name = "DE_DESCRIPCION")
    private String descripcion;

    @Column(name = "NU_MONTO_INICIAL")
    private Double montoInicial;

    @Column(name = "NU_MONTO_FINAL")
    private Double montoFinal;

    @Column(name = "NU_CELULAR")
    private String nroCelular;

    @Column(name = "DI_LINKDN")
    private String linkLinkedin;

    @Column(name = "DI_GITHUB")
    private String linkGithub;

    @Column(name = "FE_CREACION", updatable = false)
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fechaCreacion;

    /* Relationship */
    /*@OneToMany
    @JoinColumn(name = "ID_TALENTO_W")
    private List<HabilidadBlanda> habilidadesBlandas;*/

}