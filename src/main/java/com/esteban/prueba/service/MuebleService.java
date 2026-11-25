package com.esteban.prueba.service;

import com.esteban.prueba.model.Mueble;
import com.esteban.prueba.repository.MuebleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MuebleService {
    private final MuebleRepository muebleRepository;

    public MuebleService(MuebleRepository muebleRepository) {

        this.muebleRepository = muebleRepository;
    }

    // LISTAR TODOS
    public List<Mueble> traerTodos() {

        return muebleRepository.findAll();
    }

    // TRAER UNO
    public Optional<Mueble> traerUno(int id) {
        return muebleRepository.findById(id);
    }

    // GUARDAR
    public Mueble guardar(Mueble mueble) {
        return muebleRepository.save(mueble);
    }

    // EDITAR
    public Optional<Mueble> editar(int id, Mueble muebleActualizado) {
        return muebleRepository.findById(id)
                .map(m -> {
                    m.setNombre(muebleActualizado.getNombre());
                    m.setColor(muebleActualizado.getColor());
                    m.setMaterial(muebleActualizado.getMaterial());
                    m.setPrecio(muebleActualizado.getPrecio());
                    m.setStock(muebleActualizado.getStock());
                    return muebleRepository.save(m);
                });
    }

    // ELIMINAR
    public boolean eliminar(int id) {
        if (muebleRepository.existsById(id)) {
            muebleRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    //CASO: PRECIO POR RANGO
    public List<Mueble> rangoPrecio(double min, double max) {
        return muebleRepository.findAll()
                .stream()
                .filter(mueble -> mueble.getPrecio() >= min && mueble.getPrecio() <= max)
                .collect(Collectors.toList());
    }
}
