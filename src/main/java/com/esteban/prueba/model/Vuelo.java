package com.esteban.prueba.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "vuelo")
public class Vuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String codigo;
    private String origen;
    private String destino;
    private double precio;
    private int asientosDisponibles;
}