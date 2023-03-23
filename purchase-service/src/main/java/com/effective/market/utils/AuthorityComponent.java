package com.effective.market.utils;

import com.effective.market.dao.ProductRepository;
import com.effective.market.dao.ProfileRepository;
import com.effective.market.dao.PurchaseRepository;
import com.effective.market.entity.Product;
import com.effective.market.entity.Profile;
import com.effective.market.exception.BadRequestException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Principal;

/**
 * Компонент для проверки купил ли пользователь товар или нет
 *
 * @author YermukhanJJ
 */
@Component("A")
@RequiredArgsConstructor
public class AuthorityComponent {

    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
    private final ProfileRepository profileRepository;

    public Boolean isPurchased(@NonNull final Principal principal,
                               @NonNull final Long idProduct) {
        Profile profile = profileRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new BadRequestException("Profile not found"));
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new BadRequestException("Product not found"));

        return purchaseRepository.existsByProfileAndProductTitle(profile, product.getTitle());
    }

//    public Boolean isProfilePurchased(@NonNull final Principal principal,
//                                      @NonNull final Long idProfile) {
//        Profile requestProfile = profileRepository.findById(idProfile)
//                .orElseThrow(() -> new BadRequestException("Profile not found"));
//        Profile principalProfile = profileRepository.findByUsername(principal.getName())
//                .orElseThrow(() -> new BadRequestException("Profile not found"));
//        if (requestProfile.equals(principalProfile))
//            return purchaseRepository.existsByProfile(requestProfile);
//        else
//            return false;
//    }
}
