package com.esteban.prueba.repository;

import com.esteban.prueba.model.Mueble;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MuebleRepository extends JpaRepository<Mueble, Integer> {
}
