package com.app.bdt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class TalentoController {

    /*
     * @GetMapping
     * public ResponseEntity<Object> base() {
     * Talento talento = new Talento();
     * talento.setId(1L);
     * 
     * HabilidadBlanda habilidadBlanda = new HabilidadBlanda();
     * 
     * List<HabilidadBlanda> habilidadesBlandas = new ArrayList();
     * 
     * habilidadBlanda.setHabilidad("Puntualidad");
     * // habilidadBlanda.setTalento(talento);
     * 
     * habilidadesBlandas.add(habilidadBlanda);
     * 
     * talento.setHabilidadesBlandas(habilidadesBlandas);
     * 
     * List<Object> res = new ArrayList<>();
     * 
     * res.add(talento);
     * res.add(habilidadBlanda);
     * 
     * return new ResponseEntity<>(habilidadBlanda, HttpStatus.OK);
     * }
     */
}
