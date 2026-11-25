package com.esteban.prueba.controller;

import com.esteban.prueba.model.Vuelo;
import com.esteban.prueba.service.VueloService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vuelos")
public class VueloController {

    private final VueloService vueloService;

    public VueloController(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    // LISTAR TODOS
    @GetMapping
    public List<Vuelo> verTodos() {
        return vueloService.traerTodos();
    }

    // TRAER UNO
    @GetMapping("/{id}")
    public ResponseEntity<Vuelo> traerUno(@PathVariable int id) {
        return vueloService.traerUno(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREAR UNO
    @PostMapping
    public ResponseEntity<Vuelo> guardar(@RequestBody Vuelo vuelo) {
        Vuelo nuevo = vueloService.guardar(vuelo);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    // EDITAR UNO
    @PutMapping("/{id}")
    public ResponseEntity<Vuelo> editar(@PathVariable int id, @RequestBody Vuelo vuelo) {
        return vueloService.editar(id, vuelo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ELIMINAR UNO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        if (vueloService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //CASO: VUELOS HACIA UN DESTINO
    @GetMapping("/destino")
    public List<Vuelo> porDestino(@RequestParam String ciudad) {
        return vueloService.destino(ciudad);
    }
}
