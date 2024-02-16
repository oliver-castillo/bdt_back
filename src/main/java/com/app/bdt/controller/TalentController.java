package com.app.bdt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.bdt.model.dto.TalentDto;
import com.app.bdt.model.entity.Talent;
import com.app.bdt.model.mapper.ITalentMapper;
import com.app.bdt.service.serviceImpl.TalentService;

@RestController
@RequestMapping("/talent")
@CrossOrigin(origins = "http://localhost:4200")
public class TalentController {

    @Autowired
    private TalentService talentService;

    @Autowired
    private ITalentMapper talentMapper;

    @GetMapping
    public ResponseEntity<List<Talent>> getTalentos() {
        List<Talent> talents = talentService.getTalents();
        return ResponseEntity.ok(talents);
    }

    /*
     * @PostMapping("/create")
     * public ResponseEntity<Talent> createTalento(@RequestBody Talent talent) {
     * Talent createdTalent = talentoService.createTalento(talent);
     * return ResponseEntity.ok(createdTalent);
     * }
     */

    @GetMapping("/to_talent")
    public ResponseEntity<Object> testMapper(@RequestBody TalentDto talentDto) {
        System.out.println(talentMapper.toTalent(talentDto));
        return ResponseEntity.ok(talentMapper.toTalent(talentDto));
    }

    /*
     * @GetMapping
     * public ResponseEntity<Object> base() {
     * Talent talento = new Talent();
     * talento.setId(1L);
     *
     * SoftSkill habilidadBlanda = new SoftSkill();
     *
     * List<SoftSkill> habilidadesBlandas = new ArrayList();
     *
     * habilidadBlanda.setHabilidad("Puntualidad");
     * // habilidadBlanda.setTalent(talento);
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
