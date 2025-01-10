package com.example.productapi.service;

import com.example.productapi.entity.Product;
import com.example.productapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servicio que maneja la lógica de negocio para los productos.
 * Se comunica con el repositorio para realizar operaciones sobre la base de datos.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    // Inyección de dependencias a través del constructor
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Añade un nuevo producto a la base de datos.
     *
     * @param product El producto a añadir.
     * @return El producto guardado.
     */
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Obtiene un producto por su ID.
     *
     * @param id El ID del producto a buscar.
     * @return Un Optional que contiene el producto si existe.
     */
    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    /**
     * Obtiene todos los productos de la base de datos.
     *
     * @return Todos los productos en forma de iterable.
     */
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
