package com.esteban.prueba.controller;

import com.esteban.prueba.model.Mueble;
import com.esteban.prueba.service.MuebleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/muebles")
public class MuebleController {

    private final MuebleService muebleService;

    public MuebleController(MuebleService muebleService) {
        this.muebleService = muebleService;
    }

    // LISTAR TODOS
    @GetMapping
    public List<Mueble> verTodos() {
        return muebleService.traerTodos();
    }

    // TRAER UNO
    @GetMapping("/{id}")
    public ResponseEntity<Mueble> traerUno(@PathVariable int id) {
        return muebleService.traerUno(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREAR UNO
    @PostMapping
    public ResponseEntity<Mueble> guardar(@RequestBody Mueble mueble) {
        Mueble nuevo = muebleService.guardar(mueble);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    // EDITAR UNO
    @PutMapping("/{id}")
    public ResponseEntity<Mueble> editar(@PathVariable int id, @RequestBody Mueble mueble) {
        return muebleService.editar(id, mueble)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ELIMINAR UNO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        if (muebleService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //CASO: PRECIO POR RANGO
    @GetMapping("/precio")
    public List<Mueble> rangoPrecio(@RequestParam(defaultValue = "10") double min,
                                    @RequestParam(defaultValue = "100") double max) {
        return muebleService.rangoPrecio(min, max);
    }
}
