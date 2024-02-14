package com.app.bdt.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import com.app.bdt.service.IStoredProceduresService;
import org.springframework.stereotype.Service;

import com.app.bdt.model.response.CiudadResp;
import com.app.bdt.model.response.IdiomaResp;
import com.app.bdt.model.response.MonedaResp;
import com.app.bdt.model.response.NivelResp;
import com.app.bdt.model.response.PaisResp;
import com.app.bdt.model.response.PerfilResp;
import com.app.bdt.model.response.RolResp;
import com.app.bdt.repository.IMasterRepository;
import com.app.bdt.service.IMasterService;

@Service
@Transactional
public class MasterService implements IMasterService {

  Logger log = Logger.getLogger(this.getClass().getName());

  private final IMasterRepository masterRepository;
  private final EntityManager entityManager;

  private final IStoredProceduresService storedProceduresService;

  public MasterService(IMasterRepository masterRepository, EntityManager entityManager, IStoredProceduresService storedProceduresService) {
    this.masterRepository = masterRepository;
    this.entityManager = entityManager;
    this.storedProceduresService = storedProceduresService;
  }

  @Override
  public List<RolResp> obtenerRoles() {
    try {
      List<Object[]> objetos = storedProceduresService.obtenerObjetos("SP_OBTENER_ROLES");
      List<RolResp> roles = new ArrayList<>();
      for (Object[] objeto : objetos) {
        int id = Integer.parseInt(objeto[0].toString());
        String rol = (String) objeto[1];
        String abreviatura = (String) objeto[2];
        roles.add(new RolResp(id, rol, abreviatura));
      }
      return roles;
    } catch (Exception e) {
      log.warning(e.getMessage());
      return null;
    }
  }

  @Override
  public List<MonedaResp> obtenerMonedas() {
    try {
      List<Object[]> objetos = storedProceduresService.obtenerObjetos("SP_OBTENER_MONEDAS");
      List<MonedaResp> monedas = new ArrayList<>();
      for (Object[] objeto : objetos) {
        int id = Integer.parseInt(objeto[0].toString());
        String moneda = (String) objeto[1];
        String abreviatura = (String) objeto[2];
        monedas.add(new MonedaResp(id, moneda, abreviatura));
      }
      return monedas;
    } catch (Exception e) {
      log.warning(e.getMessage());
      return null;
    }
  }

  @Override
  public List<PerfilResp> obtenerPerfiles() {
    try {
      List<Object[]> objetos = storedProceduresService.obtenerObjetos("SP_OBTENER_PERFILES");
      List<PerfilResp> perfiles = new ArrayList<>();
      for (Object[] objeto : objetos) {
        int id = Integer.parseInt(objeto[0].toString());
        String perfil = (String) objeto[1];
        String abreviatura = (String) objeto[2];
        perfiles.add(new PerfilResp(id, perfil, abreviatura));
      }
      return perfiles;
    } catch (Exception e) {
      log.warning(e.getMessage());
      return null;
    }
  }

  @Override
  public List<IdiomaResp> obtenerIdiomas() {
    try {
      List<Object[]> objetos = storedProceduresService.obtenerObjetos("SP_OBTENER_IDIOMAS");
      List<IdiomaResp> idiomas = new ArrayList<>();
      for (Object[] objeto : objetos) {
        int id = Integer.parseInt(objeto[0].toString());
        String idioma = (String) objeto[1];
        String abreviatura = (String) objeto[2];
        idiomas.add(new IdiomaResp(id, idioma, abreviatura));
      }
      return idiomas;
    } catch (Exception e) {
      log.warning(e.getMessage());
      return null;
    }
  }

  @Override
  public List<NivelResp> obtenerNiveles() {
    try {
      List<Object[]> objetos = storedProceduresService.obtenerObjetos("SP_OBTENER_NIVELES");
      List<NivelResp> niveles = new ArrayList<>();
      for (Object[] objeto : objetos) {
        int id = Integer.parseInt(objeto[0].toString());
        String nivel = (String) objeto[1];
        niveles.add(new NivelResp(id, nivel));
      }
      return niveles;
    } catch (Exception e) {
      log.warning(e.getMessage());
      return null;
    }
  }

}
