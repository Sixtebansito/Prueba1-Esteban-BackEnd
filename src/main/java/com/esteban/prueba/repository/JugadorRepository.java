package com.esteban.prueba.repository;

import com.esteban.prueba.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadorRepository extends JpaRepository<Jugador, Integer> {

}
