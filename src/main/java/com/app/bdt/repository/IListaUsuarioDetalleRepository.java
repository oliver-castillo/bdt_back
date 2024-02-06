package com.app.bdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.bdt.model.entity.ListaUsuarioDetalle;

@Repository
public interface IListaUsuarioDetalleRepository extends JpaRepository<ListaUsuarioDetalle, Long> {

}
