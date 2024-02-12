package com.app.bdt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.bdt.service.IMasterService;

@RestController
@RequestMapping("/paises")
public class MasterController {

    private final IMasterService masterService;

    public MasterController(IMasterService masterService) {
        this.masterService = masterService;
    }

    @GetMapping()
    public ResponseEntity<Object> findAll() {
        try {
            return new ResponseEntity<>(masterService.getPaises(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * @GetMapping("/{id}")
     * public ResponseEntity<?> find(@PathVariable Integer id) {
     * try {
     * //TODO Implement Your Logic To Get Data From Service Layer Or Directly From
     * Repository Layer
     * return new ResponseEntity<>("GetOne Result", HttpStatus.OK);
     * } catch (Exception e) {
     * return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
     * }
     * }
     *
     * @PostMapping()
     * public ResponseEntity<?> create(@RequestBody Dto dto) {
     * try {
     * //TODO Implement Your Logic To Save Data And Return Result Through
     * ResponseEntity
     * return new ResponseEntity<>("Create Result", HttpStatus.OK);
     * } catch (Exception e) {
     * return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
     * }
     * }
     *
     * @PutMapping()
     * public ResponseEntity<?> update(@RequestBody Dto dto) {
     * try {
     * //TODO Implement Your Logic To Update Data And Return Result Through
     * ResponseEntity
     * return new ResponseEntity<>("Update Result", HttpStatus.OK);
     * } catch (Exception e) {
     * return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
     * }
     * }
     *
     * @DeleteMapping("/{id}")
     * public ResponseEntity<?> delete(@PathVariable Integer id) {
     * try {
     * //TODO Implement Your Logic To Destroy Data And Return Result Through
     * ResponseEntity
     * return new ResponseEntity<>("Destroy Result", HttpStatus.OK);
     * } catch (Exception e) {
     * return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
     * }
     * }
     */
}
