package com.effective.market.service;

import com.effective.market.model.dto.PurchaseDto;
import com.effective.market.model.dto.PurchaseStoryDto;

import java.security.Principal;
import java.util.List;

/**
 * Сервис покупки товаров
 *
 * @author YermukhanJJ
 */
public interface PurchaseService {
    /**
     * Метод покупки товаров
     *
     * @param principal
     * @param purchaseDto
     * @param idDiscount
     */
    void purchase(Principal principal, PurchaseDto purchaseDto, Long idDiscount);

    /**
     * Метод для вывода историй покупок указанного пользователя
     *
     * @param profileId
     * @return {@link List<PurchaseStoryDto>}
     */
    List<PurchaseStoryDto> getAllPurchaseByProfile(Long profileId);

    /**
     * Метод вывода историй покупок текущего пользователя
     *
     * @param principal
     * @return {@link List<PurchaseStoryDto>}
     */
    List<PurchaseStoryDto> getAllPrincipalsPurchase(Principal principal);

    /**
     * Метод для возврата товара
     *
     * @param purchaseStoryId
     * @param principal
     */
    void refund(Long purchaseStoryId, Principal principal);
}
