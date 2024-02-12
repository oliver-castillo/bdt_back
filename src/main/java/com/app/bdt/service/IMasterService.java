package com.app.bdt.service;

import java.util.List;

import com.app.bdt.model.response.Ciudad;
import com.app.bdt.model.response.Pais;

public interface IMasterService {

    List<Pais> getPaises();

    List<Ciudad> getCiudades();

}
