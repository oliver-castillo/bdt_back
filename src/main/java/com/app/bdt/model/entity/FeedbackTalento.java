package com.app.bdt.model.entity;

import java.io.Serializable;

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
@Table(name = "feedbacktalento")
public class FeedbackTalento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feedbacktalento_sequence")
    @SequenceGenerator(name = "feedbacktalento_sequence", sequenceName = "feedbacktalento_sequence", allocationSize = 100)
    private Long id;

    private Talento talento;

    private Integer nroEstrellas;

    private String descripcion;

}