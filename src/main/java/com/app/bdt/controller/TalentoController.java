package com.app.bdt.controller;

import com.app.bdt.model.entity.Talento;
import com.app.bdt.service.serviceImpl.TalentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/talento")
@CrossOrigin(origins = "http://localhost:4200")
public class TalentoController {

    @Autowired
    private TalentoService talentoService;

    @GetMapping
    public ResponseEntity<List<Talento>> getTalentos() {
        List<Talento> talents = talentoService.getTalentos();
        return ResponseEntity.ok(talents);
    }

    @PostMapping("/create")
    public ResponseEntity<Talento> createTalento(@RequestBody Talento talento) {
        Talento createdTalento = talentoService.createTalento(talento);
        return ResponseEntity.ok(createdTalento);
    }

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
