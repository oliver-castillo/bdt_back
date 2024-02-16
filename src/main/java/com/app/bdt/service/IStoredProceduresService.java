package com.app.bdt.service;

import java.util.List;

public interface IStoredProceduresService {

  public List<Object[]> getObjects(String storedProcedureName);
}
