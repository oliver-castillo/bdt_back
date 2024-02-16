package com.app.bdt.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
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
@Table(name = "BT_TD_HABILIDAD_TECNICA")
public class TechnicalSkills implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "habilidadtecnica_sequence")
    @SequenceGenerator(name = "habilidadtecnica_sequence", sequenceName = "habilidadtecnica_sequence", allocationSize = 100)
    @Column(name = "ID_HABILIDAD_TECNICA")
    private Long id;

    @Column(name = "NO_HABILIDAD")
    private String skill;

    @ManyToOne
    @JoinColumn(name = "ID_TALENTO")
    private Talent talent;

    @Column(name = "NU_ANIOS")
    private Integer years;

}