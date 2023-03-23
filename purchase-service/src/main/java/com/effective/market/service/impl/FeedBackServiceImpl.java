package com.effective.market.service.impl;

import com.effective.market.dao.FeedBackRepository;
import com.effective.market.dao.ProductRepository;
import com.effective.market.dao.ProfileRepository;
import com.effective.market.entity.Feedback;
import com.effective.market.entity.Product;
import com.effective.market.entity.Profile;
import com.effective.market.exception.BadRequestException;
import com.effective.market.model.dto.FeedBackDto;
import com.effective.market.service.FeedBackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedBackServiceImpl implements FeedBackService {

    private final ProfileRepository profileRepository;
    private final ProductRepository productRepository;
    private final FeedBackRepository feedBackRepository;

    @Override
    public void feedback(Principal principal, Long idProduct, FeedBackDto feedBackDto) {
        Profile profile = profileRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new BadRequestException("Profile not found"));
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new BadRequestException("Product not found"));
        Feedback feedback = new Feedback();
        feedback.setIdProfile(profile.getId());
        feedback.setIdProduct(product.getId());
        feedback.setDescription(feedBackDto.getDescription());
        feedback.setScore(feedBackDto.getScore());
        feedBackRepository.save(feedback);
        Integer countScore = feedBackRepository.countFeedbackByIdProduct(idProduct);
        Integer sumScore = feedBackRepository.sumScoreByIdProduct(idProduct);
        Double score = ((double) sumScore) / ((double) countScore);
        product.setScore(score);
        productRepository.save(product);

    }

    @Override
    public List<Feedback> getAllFeedBackByIdProduct(Long idProduct) {
        return feedBackRepository.findAllByIdProduct(idProduct);
    }
}
