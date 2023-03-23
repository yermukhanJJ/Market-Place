package com.effective.market.service;

import com.effective.market.entity.Feedback;
import com.effective.market.model.dto.FeedBackDto;

import java.security.Principal;
import java.util.List;

/**
 * Сервис отзыва
 *
 * @author YermukhanJJ
 */
public interface FeedBackService {

    /**
     * Метод для оценки и отзыва
     *
     * @param principal
     * @param idProduct
     * @param feedBackDto
     */
    void feedback(Principal principal, Long idProduct, FeedBackDto feedBackDto);

    /**
     * Метод вывода отзывов по указанному товару
     *
     * @param idProduct
     * @return {@link List<Feedback>}
     */
    List<Feedback> getAllFeedBackByIdProduct(Long idProduct);
}
