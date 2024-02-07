package com.app.bdt.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "habilidadtecnica")
public class HabilidadTecnica implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "habilidadtecnica_sequence")
    @SequenceGenerator(name = "habilidadtecnica_sequence", sequenceName = "habilidadtecnica_sequence", allocationSize = 100)
    private Long id;

    private String habilidad;

    @ManyToOne
    @JoinColumn(name = "idTalento")
    private Talento talento;

    private Integer anios;
}