package com.effective.market.service;

import com.effective.market.dto.ProductUpdateDto;
import com.effective.market.entity.Product;

import java.util.List;

/**
 * Сервис для управлений данных о товарах
 * @author YermukhanJJ
 */
public interface ProductService {
    /**
     * Метод для обновлений данных о товарах
     * @param id
     * @param productUpdateDto
     */
    void updateProductInfo(Long id, ProductUpdateDto productUpdateDto);

    /**
     * Метод для вывода всех товаров
     * @return {@link List<Product>}
     */
    List<Product> getAllProducts();

    /**
     * Метод для удалений товара по id
     * @param id
     */
    void deleteProduct(Long id);
}
