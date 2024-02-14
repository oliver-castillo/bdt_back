package com.app.bdt.service;

import javax.persistence.EntityManager;
import java.util.List;

public interface IStoredProceduresService {

  public List<Object[]> obtenerObjetos(String nombreStoredProcedure);
}
