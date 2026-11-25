package com.esteban.prueba.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "mueble")
public class Mueble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String material;
    private String color;
    private double precio;
    private int stock;
}
