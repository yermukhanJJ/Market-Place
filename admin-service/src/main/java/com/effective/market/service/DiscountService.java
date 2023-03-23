package com.effective.market.service;

import com.effective.market.dto.DiscountCreateDto;
import com.effective.market.dto.RequestOrganizationDto;
import com.effective.market.dto.RequestProductDto;
import com.effective.market.entity.Discount;

import java.util.List;

/**
 * Сервис для управление скидками для товаров
 * @author YermukhanJJ
 */
public interface DiscountService {
    /**
     * Метод для созданий скидки
     * @param discountCreateDto
     */
    void createDiscount(DiscountCreateDto discountCreateDto);

    /**
     * Метод для обновлений данных скидки
     * @param id
     * @param discountCreateDto
     */
    void updateDiscount(Long id, DiscountCreateDto discountCreateDto);

    /**
     * Метод для вывода всех скидок
     * @return {@link List<Discount>}
     */
    List<Discount> getAllDiscounts();

    /**
     * Метод для добавлений скидки на указанных товаров
     * @param id
     * @param requestProductDto
     */
    void addDiscountInfoToProducts(Long id, RequestProductDto requestProductDto);

    /**
     * Метод для добавлений скидки на товаров указанный организаций
     * @param id
     * @param requestOrganizationDto
     */
    void addDiscountInfoToOrganizations(Long id, RequestOrganizationDto requestOrganizationDto);
}
