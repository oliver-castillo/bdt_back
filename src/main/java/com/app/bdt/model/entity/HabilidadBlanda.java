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
@Table(name = "BT_TD_HABILIDAD_BLANDA")
public class HabilidadBlanda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "habilidadblanda_sequence")
    @SequenceGenerator(name = "habilidadblanda_sequence", sequenceName = "habilidadblanda_sequence", allocationSize = 100)
    @Column(name = "ID_HABILIDAD_BLANDA")
    private Long id;

    @Column(name = "NO_HABILIDAD")
    private String habilidad;

    /*
     * @ManyToOne
     * 
     * @JoinColumn(name = "ID_TALENTO")
     * private Talento talento;
     */

}