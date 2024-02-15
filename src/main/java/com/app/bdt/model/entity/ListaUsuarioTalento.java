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
@Table(name = "BT_TX_LISTA_USUARIO_TALENTO")
public class ListaUsuarioTalento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "listausuariotalento_sequence")
    @SequenceGenerator(name = "listausuariotalento_sequence", sequenceName = "listausuariotalento_sequence", allocationSize = 100)
    @Column(name = "ID_LISTA_USUARIO_DETALLE")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_LISTA_USUARIO")
    private ListaUsuario listaUsuario;

    @ManyToOne
    @JoinColumn(name = "ID_TALENTO")
    private Talent talent;

}