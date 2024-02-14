package com.app.bdt.service;

import java.util.List;

import com.app.bdt.model.response.CiudadResp;
import com.app.bdt.model.response.IdiomaResp;
import com.app.bdt.model.response.MonedaResp;
import com.app.bdt.model.response.NivelResp;
import com.app.bdt.model.response.PaisResp;
import com.app.bdt.model.response.PerfilResp;
import com.app.bdt.model.response.RolResp;

public interface IMasterService {

    List<Object[]> obtenerObjetos(String nombreStoredProcedure);

    List<PaisResp> obtenerPaises();

    List<CiudadResp> obtenerCiudades();

    List<RolResp> obtenerRoles();

    List<MonedaResp> obtenerMonedas();

    List<PerfilResp> obtenerPerfiles();

    List<IdiomaResp> obtenerIdiomas();

    List<NivelResp> obtenerNiveles();

    PaisResp obtenerPaisPorId(int id);

}
