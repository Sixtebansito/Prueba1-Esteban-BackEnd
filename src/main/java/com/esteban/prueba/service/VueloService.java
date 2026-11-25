package com.esteban.prueba.service;

import com.esteban.prueba.model.Vuelo;
import com.esteban.prueba.repository.VueloRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VueloService {
    private final VueloRepository vueloRepository;

    public VueloService(VueloRepository vueloRepository) {

        this.vueloRepository = vueloRepository;
    }

    // LISTAR TODOS
    public List<Vuelo> traerTodos() {

        return vueloRepository.findAll();
    }

    // TRAER UNO
    public Optional<Vuelo> traerUno(int id) {
        return vueloRepository.findById(id);
    }

    // GUARDAR
    public Vuelo guardar(Vuelo vuelo) {
        return vueloRepository.save(vuelo);
    }

    // EDITAR
    public Optional<Vuelo> editar(int id, Vuelo vueloActualizado) {
        return vueloRepository.findById(id)
                .map(v -> {
                    v.setAsientosDisponibles(vueloActualizado.getAsientosDisponibles());
                    v.setCodigo(vueloActualizado.getCodigo());
                    v.setDestino(vueloActualizado.getDestino());
                    v.setOrigen(vueloActualizado.getOrigen());
                    v.setPrecio(vueloActualizado.getPrecio());
                    return vueloRepository.save(v);
                });
    }

    // ELIMINAR
    public boolean eliminar(int id) {
        if (vueloRepository.existsById(id)) {
            vueloRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    //CASO: Vuelos hacia destino
    public List<Vuelo> destino(String ciudad) {
        return vueloRepository.findAll()
                .stream()
                .filter(vuelo -> ciudad.equalsIgnoreCase(vuelo.getDestino()))
                .collect(Collectors.toList());
    }

}
