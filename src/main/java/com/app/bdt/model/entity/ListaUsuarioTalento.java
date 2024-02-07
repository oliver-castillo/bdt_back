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
@Table(name = "listausuariodetalle")
public class ListaUsuarioTalento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "listausuariodetalle_sequence")
    @SequenceGenerator(name = "listausuariodetalle_sequence", sequenceName = "listausuariodetalle_sequence", allocationSize = 100)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idListaUsuario")
    private ListaUsuario listaUsuario;

    @ManyToOne
    @JoinColumn(name = "idTalento")
    private Talento talento;

}