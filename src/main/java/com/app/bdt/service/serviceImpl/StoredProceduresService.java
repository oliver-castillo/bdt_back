package com.app.bdt.service.serviceImpl;

import com.app.bdt.exceptions.InternalServerError;
import com.app.bdt.service.IStoredProceduresService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class StoredProceduresService implements IStoredProceduresService {

  Logger log = Logger.getLogger(this.getClass().getName());

  private final EntityManager entityManager;

  public StoredProceduresService(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public List<Object[]> obtenerObjetos(String nombreStoredProcedure) {
    try {
      StoredProcedureQuery storedProcedure = entityManager
              .createStoredProcedureQuery(
                      nombreStoredProcedure)
              .registerStoredProcedureParameter(1, Object.class, ParameterMode.REF_CURSOR);
      storedProcedure.execute();
      List<Object[]> objetos = storedProcedure.getResultList();
      return objetos;
    } catch (Exception e) {
      throw new InternalServerError("Error en el servidor: "+ e.getMessage());
    }
  }
}
