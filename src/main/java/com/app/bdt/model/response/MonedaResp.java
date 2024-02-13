package com.app.bdt.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonedaResp {

    private int id;

    private String moneda;

    private String abreviatura;

}
