import axios from "axios";

/**
 * Interfaz que define la estructura de un producto.
 */
export interface Product {
  id: number;
  name: string;
}

const API_URL = "http://localhost:8080/products";

/**
 * Obtiene los productos desde el backend.
 * Lanza un error si la solicitud falla.
 */
export const getProducts = async (): Promise<Product[]> => {
  try {
    const response = await axios.get<Product[]>(API_URL);
    return response.data;
  } catch (error) {
    console.error("Error al obtener productos:", error);
    throw new Error("No se pudieron cargar los productos");
  }
};
