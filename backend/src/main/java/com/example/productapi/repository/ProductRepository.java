package com.example.productapi.repository;

import com.example.productapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz que maneja las operaciones CRUD de la entidad Product.
 * Extiende JpaRepository, que proporciona operaciones estándar para interactuar con la base de datos.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
