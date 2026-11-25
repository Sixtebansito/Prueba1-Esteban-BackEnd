package com.esteban.prueba.service;

import com.esteban.prueba.model.Jugador;
import com.esteban.prueba.repository.JugadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JugadorService {

    private final JugadorRepository jugadorRepository;

    public JugadorService(JugadorRepository jugadorRepository) {

        this.jugadorRepository = jugadorRepository;
    }

    // LISTAR TODOS
    public List<Jugador> traerTodos() {

        return jugadorRepository.findAll();
    }

    // TRAER UNO
    public Optional<Jugador> traerUno(int id) {
        return jugadorRepository.findById(id);
    }

    // GUARDAR
    public Jugador guardar(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    // EDITAR
    public Optional<Jugador> editar(int id, Jugador jugadorActualizado) {
        return jugadorRepository.findById(id)
                .map(j -> {
                    j.setNombre(jugadorActualizado.getNombre());
                    j.setEquipo(jugadorActualizado.getEquipo());
                    j.setPosicion(jugadorActualizado.getPosicion());
                    j.setEdad(jugadorActualizado.getEdad());
                    j.setGoles(jugadorActualizado.getGoles());
                    return jugadorRepository.save(j);
                });
    }

    // ELIMINAR
    public boolean eliminar(int id) {
        if (jugadorRepository.existsById(id)) {
            jugadorRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    //CASO: TOTAL GOLES
    public int totalGoles() {
        return jugadorRepository.findAll()
                .stream()
                .mapToInt(Jugador::getGoles)
                .sum();
    }

}
