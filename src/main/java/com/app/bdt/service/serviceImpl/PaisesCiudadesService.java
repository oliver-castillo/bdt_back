package com.app.bdt.service.serviceImpl;

import com.app.bdt.model.response.CiudadResp;
import com.app.bdt.model.response.PaisResp;
import com.app.bdt.service.IPaisesCiudadesService;
import com.app.bdt.service.IStoredProceduresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PaisesCiudadesService implements IPaisesCiudadesService {

  Logger log = Logger.getLogger(this.getClass().getName());
  private final IStoredProceduresService storedProcedures;

  @Autowired
  public PaisesCiudadesService(IStoredProceduresService storedProcedures) {
    this.storedProcedures = storedProcedures;
  }

  @Override
  public List<PaisResp> obtenerPaises() {
    try {
      List<Object[]> objetos = storedProcedures.obtenerObjetos("SP_OBTENER_PAISES");
      List<PaisResp> paises = new ArrayList<>();
      for (Object[] objeto : objetos) {
        int id = Integer.parseInt(objeto[0].toString());
        String pais = (String) objeto[1];
        String abreviatura = (String) objeto[2];
        paises.add(new PaisResp(id, pais, abreviatura));
      }
      return paises;
    } catch (Exception e) {
      log.warning(e.getMessage());
      return null;
    }
  }

  @Override
  public PaisResp obtenerPaisPorId(int id) {
    PaisResp pais = new PaisResp();
    List<PaisResp> paises = obtenerPaises();
    System.out.println(paises);
    for (PaisResp p : paises) {
      if (p.getId() == id) {
        pais = p;
      }
    }
    return pais;
  }

  @Override
  public List<CiudadResp> obtenerCiudades() {
    try {
      List<Object[]> objetos = storedProcedures.obtenerObjetos("SP_OBTENER_CIUDADES");
      List<CiudadResp> ciudades = new ArrayList<>();
      for (Object[] objeto : objetos) {
        int id = Integer.parseInt(objeto[0].toString());
        String ciudad = (String) objeto[1];
        int paisId = Integer.parseInt(objeto[2].toString());
        ciudades.add(new CiudadResp(id, ciudad, paisId));
      }
      return ciudades;
    } catch (Exception e) {
      log.warning(e.getMessage());
      return Collections.emptyList();
    }
  }

  @Override
  public CiudadResp obtenerCiudadPorId(int id) {
    List<CiudadResp> ciudades = obtenerCiudades();
    CiudadResp ciudad = new CiudadResp();
    for (CiudadResp c : ciudades) {
      if (c.getId() == id) {
        ciudad = c;
      }
    }
    return ciudad;
  }

  @Override
  public List<CiudadResp> obtenerCiudadesPorPais(int id) {
    List<CiudadResp> ciudades = obtenerCiudades();
    List<CiudadResp> ciudadesPorPais = new ArrayList<>();
    for (CiudadResp ciudad : ciudades) {
      if (ciudad.getPaisId() == id) {
        ciudadesPorPais.add(ciudad);
      }
    }
    return ciudadesPorPais;
  }
}
