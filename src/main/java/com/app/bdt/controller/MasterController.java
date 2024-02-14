package com.app.bdt.controller;

import java.util.ArrayList;
import java.util.List;

import com.app.bdt.service.IPaisesCiudadesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.bdt.service.IMasterService;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:4200")
public class MasterController {

  private final IMasterService masterService;
  private final IPaisesCiudadesService paisesCiudadesService;

  public MasterController(IMasterService masterService, IPaisesCiudadesService paisesCiudadesService) {
    this.masterService = masterService;
    this.paisesCiudadesService = paisesCiudadesService;
  }

  @GetMapping("/{descripcion}")
  public ResponseEntity<Object> mostrarDatos(@PathVariable(name = "descripcion") String descripcion) {
    List<?> resultado = new ArrayList<>();
    switch (descripcion) {
      case "paises":
        resultado = paisesCiudadesService.obtenerPaises();
        break;
      case "ciudades":
        resultado = paisesCiudadesService.obtenerCiudades();
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

  @GetMapping("/pais/{id}")
  public ResponseEntity<Object> obtenerPaisPorId(@PathVariable int id) {
    return new ResponseEntity<>(paisesCiudadesService.obtenerPaisPorId(id), HttpStatus.OK);
  }

  @GetMapping("/ciudad/{id}")
  public ResponseEntity<Object> obtenerCiudadPorId(@PathVariable int id) {
    return new ResponseEntity<>(paisesCiudadesService.obtenerCiudadPorId(id), HttpStatus.OK);
  }

  @GetMapping("/pais/{id}/ciudades")
  public ResponseEntity<Object> obtenerCiudadesPorPais(@PathVariable int id) {
    return new ResponseEntity<>(paisesCiudadesService.obtenerCiudadesPorPais(id), HttpStatus.OK);
  }

}
