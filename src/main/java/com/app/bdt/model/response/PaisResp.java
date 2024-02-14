package com.app.bdt.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaisResp {

    private int id;

    private String pais;

    private String abreviatura;

    private List<CiudadResp> ciudades;

}