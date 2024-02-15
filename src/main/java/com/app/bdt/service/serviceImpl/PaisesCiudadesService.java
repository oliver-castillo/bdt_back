package com.app.bdt.service.serviceImpl;

import com.app.bdt.exceptions.NotFoundException;
import com.app.bdt.model.response.CiudadResp;
import com.app.bdt.model.response.PaisResp;
import com.app.bdt.service.IPaisesCiudadesService;
import com.app.bdt.service.IStoredProceduresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PaisesCiudadesService implements IPaisesCiudadesService {

  private final IStoredProceduresService storedProcedures;

  @Autowired
  public PaisesCiudadesService(IStoredProceduresService storedProcedures) {
    this.storedProcedures = storedProcedures;
  }

  @Override
  public List<PaisResp> obtenerPaises() {
    List<Object[]> objetos = storedProcedures.obtenerObjetos("SP_OBTENER_PAISES");
    if (objetos == null || objetos.isEmpty()) {
      return Collections.emptyList();
    }
    List<PaisResp> paises = new ArrayList<>();
    for (Object[] objeto : objetos) {
      int id = ((Number) objeto[0]).intValue();
      String pais = (String) objeto[1];
      String abreviatura = (String) objeto[2];
      paises.add(new PaisResp(id, pais, abreviatura));
    }
    return paises;
  }

  @Override
  public PaisResp obtenerPaisPorId(int id) {
    for (PaisResp pais : obtenerPaises()) {
      if (pais.getId() == id) {
        return pais;
      }
    }
    throw new NotFoundException("No se encontró el registro.");
  }

  @Override
  public List<CiudadResp> obtenerCiudades() {
    List<Object[]> objetos = storedProcedures.obtenerObjetos("SP_OBTENER_CIUDADES");
    List<CiudadResp> ciudades = new ArrayList<>();
    for (Object[] objeto : objetos) {
      int id = Integer.parseInt(objeto[0].toString());
      String ciudad = (String) objeto[1];
      int paisId = Integer.parseInt(objeto[2].toString());
      ciudades.add(new CiudadResp(id, ciudad, paisId));
    }
    return ciudades;
  }

  @Override
  public CiudadResp obtenerCiudadPorId(int id) {
    for (CiudadResp ciudad : obtenerCiudades()) {
      if (ciudad.getId() == id) {
        return ciudad;
      }
    }
    throw new NotFoundException("No se encontró el registro");
  }

  @Override
  public List<CiudadResp> obtenerCiudadesPorPais(int id) {
    if (obtenerPaisPorId(id) != null) {
      List<CiudadResp> ciudadesPorPais = new ArrayList<>();
      for (CiudadResp ciudad : obtenerCiudades()) {
        if (ciudad.getPaisId() == id) {
          ciudadesPorPais.add(ciudad);
        }
      }
      return ciudadesPorPais;
    }
    throw new NotFoundException("No se encontró el registro");
  }

}
