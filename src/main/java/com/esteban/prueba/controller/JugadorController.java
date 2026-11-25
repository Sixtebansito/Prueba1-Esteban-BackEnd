package com.esteban.prueba.controller;

import com.esteban.prueba.model.Jugador;
import com.esteban.prueba.service.JugadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {

    private final JugadorService jugadorService;

    public JugadorController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    // LISTAR TODOS
    @GetMapping
    public List<Jugador> verTodos() {
        return jugadorService.traerTodos();
    }

    // TRAER UNO
    @GetMapping("/{id}")
    public ResponseEntity<Jugador> traerUno(@PathVariable int id) {
        return jugadorService.traerUno(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREAR UNO
    @PostMapping
    public ResponseEntity<Jugador> guardar(@RequestBody Jugador jugador) {
        Jugador nuevo = jugadorService.guardar(jugador);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    // EDITAR UNO
    @PutMapping("/{id}")
    public ResponseEntity<Jugador> editar(@PathVariable int id, @RequestBody Jugador jugador) {
        return jugadorService.editar(id, jugador)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ELIMINAR UNO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        if (jugadorService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //CASO: TOTAL GOLES
    @GetMapping("/goles")
    public int totalGoles() {
        return jugadorService.totalGoles();
    }

}
