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
@Table(name = "BT_TX_MASTER_USUARIO")
public class UserMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "masterusuario_sequence")
    @SequenceGenerator(name = "masterusuario_sequence", sequenceName = "masterusuario_sequence", allocationSize = 100)
    @Column(name = "ID_MASTER_USUARIO")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private User user;

    @Column(name = "ID_ROL")
    private Integer roleId;

}