package com.example.productapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Configuración de Redis para conectar Spring Boot con un servidor Redis.
 * Define los beans necesarios para la conexión con Redis.
 */
@Configuration
public class RedisConfig {

    /**
     * Configura la fábrica de conexiones de Redis (usando Lettuce).
     *
     * @return La fábrica de conexiones.
     */
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    /**
     * Configura el template de Redis utilizado para interactuar con la base de datos en caché.
     *
     * @return El template de Redis.
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        return template;
    }
}
