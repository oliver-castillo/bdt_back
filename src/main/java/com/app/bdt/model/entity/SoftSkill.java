package com.app.bdt.model.entity;

import java.io.Serializable;

import javax.persistence.*;

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
public class SoftSkill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HABILIDAD_BLANDA")
    private Long id;

    @Column(name = "NO_HABILIDAD")
    private String skill;

    /*@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_TALENTO")
    private Talent talent;*/

}