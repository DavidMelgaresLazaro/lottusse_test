package com.example.productapi.entity;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * La entidad Product representa un producto en la base de datos.
 * Se mapea a la tabla 't_product' y contiene los atributos que definen a un producto.
 */

@Entity
@Table(name = "t_product")
public class Product implements Serializable {
    // El ID del producto es la clave primaria y se genera automáticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // El nombre del producto es obligatorio y tiene una longitud máxima de 255 caracteres
    @Column(nullable = false, length = 255)
    private String name;

    // Getters y Setters para las propiedades de la entidad
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
