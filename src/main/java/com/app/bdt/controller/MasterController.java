package com.app.bdt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.bdt.service.IMasterService;

@RestController
@RequestMapping("")
public class MasterController {

    private final IMasterService masterService;

    public MasterController(IMasterService masterService) {
        this.masterService = masterService;
    }

    @GetMapping("/{descripcion}")
    public ResponseEntity<Object> mostrarDatos(@PathVariable(name = "descripcion") String descripcion) {
        List<?> resultado = new ArrayList<>();
        switch (descripcion) {
            case "paises":
                resultado = masterService.obtenerPaises();
                break;
            case "ciudades":
                resultado = masterService.obtenerCiudades();
                break;
            case "roles":
                resultado = masterService.obtenerRoles();
                break;
            case "monedas":
                resultado = masterService.obtenerMonedas();
                break;
            case "perfiles":
                resultado = masterService.obtenerPerfiles();
                break;
            case "idiomas":
                resultado = masterService.obtenerIdiomas();
                break;
            case "niveles":
                resultado = masterService.obtenerNiveles();
                break;
            default:
                break;
        }
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

}
