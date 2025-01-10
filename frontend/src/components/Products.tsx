import React, { useEffect, useState } from "react";
import { getProducts, Product } from "../services/productService";

/**
 * Componente que muestra la lista de productos.
 */
const ProductList: React.FC = () => {
  const [products, setProducts] = useState<Product[]>([]); // Estado para productos
  const [loading, setLoading] = useState(true); // Estado de carga
  const [error, setError] = useState<string | null>(null); // Estado de error

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const fetchedProducts = await getProducts();
        setProducts(fetchedProducts);
      } catch (err) {
        setError("Error cargando los productos");
      } finally {
        setLoading(false);
      }
    };

    fetchProducts();
  }, []); // Solo se ejecuta una vez al montar el componente

  if (loading)
    return <div className="text-center text-xl text-gray-500">Cargando...</div>;
  if (error) return <div className="text-center text-red-500">{error}</div>;

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-4xl font-bold mb-6 text-center text-indigo-600">
        Lista de Productos
      </h1>
      <ul className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
        {products.map((product) => (
          <li
            key={product.id}
            className="bg-white p-4 rounded-md shadow-md hover:bg-gray-100 transition duration-300"
          >
            <div className="flex justify-between items-center">
              <span className="text-xl font-semibold text-gray-800">
                {product.name}
              </span>
              <span className="text-sm text-gray-500">ID: {product.id}</span>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ProductList;
