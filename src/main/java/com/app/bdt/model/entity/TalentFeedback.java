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
@Table(name = "BT_TD_FEEDBACK_TALENTO")
public class TalentFeedback implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FEEDBACK")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_TALENTO")
    private Talent talent;

    @Column(name = "NU_ESTRELLAS")
    private Integer starsNumber;

    @Column(name = "DE_DESCRIPCION")
    private String description;

}