package com.example.productapi.controller;

import com.example.productapi.entity.Product;
import com.example.productapi.service.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;

/**
 * Controlador REST que maneja las peticiones HTTP relacionadas con los productos.
 * Incluye operaciones para añadir productos, obtener productos por ID y obtener todos los productos.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final RedisTemplate<String, Product> redisTemplate;
    private static final String REDIS_KEY = "Product::";

    public ProductController(ProductService productService, RedisTemplate<String, Product> redisTemplate) {
        this.productService = productService;
        this.redisTemplate = redisTemplate;
    }
    

    /**
     * Endpoint para añadir un nuevo producto.
     *
     * @param product El producto a añadir.
     * @return El producto añadido.
     */
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = productService.addProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    /**
     * Endpoint para obtener todos los productos.
     *
     * @return Una lista de productos.
     */
    @GetMapping
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    /**
     * Endpoint para obtener un producto por su ID.
     * Primero busca en Redis, si no lo encuentra lo obtiene de la base de datos y lo guarda en Redis.
     *
     * @param id El ID del producto.
     * @return El producto si se encuentra, o un error 404 si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Integer id) {
        String redisKey = REDIS_KEY + id;

        // Intentar obtener el producto de Redis
        Product cachedProduct = redisTemplate.opsForValue().get(redisKey);
        if (cachedProduct != null) {
            return ResponseEntity.ok(cachedProduct);
        }

        // Si no está en Redis, buscar en la base de datos
        Optional<Product> productOptional = productService.getProductById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            redisTemplate.opsForValue().set(redisKey, product); // Guardar en Redis
            return ResponseEntity.ok(product);
        }

        return ResponseEntity.notFound().build(); // Error 404 si no se encuentra
    }
}
