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
@Table(name = "BT_TX_MASTER_TALENTO_IDIOMA")
public class MasterTalentoIdioma implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mastertalentoidioma_sequence")
    @SequenceGenerator(name = "mastertalentoidioma_sequence", sequenceName = "mastertalentoidioma_sequence", allocationSize = 100)
    @Column(name = "ID_MASTER_TALENTO_IDIOMA")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_TALENTO")
    private Talento talento;

    @Column(name = "ID_IDIOMA")
    private Integer idiomaId;

    @Column(name = "ID_NIVEL")
    private Integer nivelId;

    @Column(name = "NU_ESTRELLAS")
    private Integer nroEstrellas;
}