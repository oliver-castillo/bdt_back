package com.app.bdt.service;

import com.app.bdt.model.response.CiudadResp;
import com.app.bdt.model.response.PaisResp;

import java.util.List;

public interface IPaisesCiudadesService {

  List<PaisResp> obtenerPaises();

  PaisResp obtenerPaisPorId(int id);

  List<CiudadResp> obtenerCiudades();

  CiudadResp obtenerCiudadPorId(int id);

  List<CiudadResp> obtenerCiudadesPorPais(int id);

}
