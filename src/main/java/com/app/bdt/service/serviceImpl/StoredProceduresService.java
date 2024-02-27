package com.app.bdt.service.serviceImpl;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Service;

import com.app.bdt.exceptions.InternalServerError;
import com.app.bdt.service.IStoredProceduresService;

@Service
public class StoredProceduresService implements IStoredProceduresService {

  Logger log = Logger.getLogger(this.getClass().getName());

  private final EntityManager entityManager;

  public StoredProceduresService(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public List<Object[]> getObjects(String storedProcedureName) {
    try {
      StoredProcedureQuery storedProcedure = entityManager
          .createStoredProcedureQuery(
              storedProcedureName)
          .registerStoredProcedureParameter(1, Object.class, ParameterMode.REF_CURSOR);
      storedProcedure.execute();
      List<Object[]> objetos = storedProcedure.getResultList();
      return objetos;
    } catch (Exception e) {
      throw new InternalServerError("Error en el servidor: " + e.getMessage());
    }
  }
}
