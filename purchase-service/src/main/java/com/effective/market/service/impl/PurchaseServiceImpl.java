package com.effective.market.service.impl;

import com.effective.market.dao.*;
import com.effective.market.entity.*;
import com.effective.market.exception.BadRequestException;
import com.effective.market.model.dto.PurchaseDto;
import com.effective.market.model.dto.PurchaseStoryDto;
import com.effective.market.model.entity.Purchase;
import com.effective.market.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class PurchaseServiceImpl implements PurchaseService {

    private final ProfileRepository profileRepository;
    private final ProductRepository productRepository;
    private final DiscountRepository discountRepository;
    private final PurchaseRepository purchaseRepository;
    private final OrganizationRepository organizationRepository;
    private static final Double FEE = 0.05D; //Комиссия из выручки 5%

    @Override
    public void purchase(Principal principal, PurchaseDto purchaseDto, Long idDiscount) {
        Profile profile = profileRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new BadRequestException("Profile not found"));
        Product product = productRepository.findByIdAndOrganizationEnabledIsTrue(purchaseDto.getIdProduct())
                .orElseThrow(() -> new BadRequestException("Product not found"));
        Discount discount = new Discount();
        if (idDiscount != null)
            discount = discountRepository.findById(idDiscount).orElse(new Discount());
        Double amount;
        if (purchaseDto.getCount() <= product.getCount()) {
            if (product.getDiscounts() != null && Objects.equals(discount.getId(), product.getDiscounts().getId()) &&
                    ZonedDateTime.now().toInstant().isBefore(product.getDiscounts().getDate().toInstant()))
                amount = (((100 - discount.getAmount()) * product.getPrice()) / 100) * purchaseDto.getCount();
            else
                amount = product.getPrice() * purchaseDto.getCount();
        } else {
            throw new BadRequestException("Bad request");
        }
        if (profile.getBalance() >= amount) {
            Purchase purchase = new Purchase();
            purchase.setProfile(profile);
            purchase.setProductTitle(product.getTitle());
            purchase.setOrganizationTitle(product.getOrganization().getTitle());
            purchase.setCount(purchaseDto.getCount());
            purchase.setAmount(amount);
            purchase.setDate(LocalDateTime.now());

            product.setCount(product.getCount() - purchaseDto.getCount());
            profile.setBalance(profile.getBalance() - amount);


            Profile recipient = profileRepository.findById(product.getIdProfile())
                    .orElseThrow(() -> new BadRequestException("Profile not found"));
            recipient.setBalance(recipient.getBalance() + (amount * (1 - FEE)));
            productRepository.save(product);
            profileRepository.save(profile);
            purchaseRepository.save(purchase);
            profileRepository.save(recipient);
            log.info("Profile: " + profile.getUsername() + " purchased + " +
                    product.getTitle() + ". Count: " + purchaseDto.getCount());
        } else {
            throw new BadRequestException("Insufficient funds");
        }
    }

    @Override
    public List<PurchaseStoryDto> getAllPurchaseByProfile(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new BadRequestException("Profile not found"));
        return getPurchaseStoryDtos(profile);
    }

    @Override
    public List<PurchaseStoryDto> getAllPrincipalsPurchase(Principal principal) {
        Profile profile = profileRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new BadRequestException("Profile not found"));
        return getPurchaseStoryDtos(profile);
    }

    @Override
    public void refund(Long purchaseStoryId, Principal principal) {
        Purchase purchase = purchaseRepository.findById(purchaseStoryId)
                .orElseThrow(() -> new BadRequestException("Purchase not found"));
        Profile profile = profileRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new BadRequestException("Profile not found"));
        if (purchase.getProfile().equals(profile)) {
            if (ZonedDateTime.now().toInstant().isBefore(purchase.getDate().plusHours(24).toInstant(ZoneOffset.UTC))) {
                Organization organization = organizationRepository.findByTitle(purchase.getOrganizationTitle())
                        .orElseThrow(() -> new BadRequestException("Organization not found"));
                Profile seller = profileRepository.findById(organization.getIdProfile())
                        .orElseThrow(() -> new BadRequestException("Seller not found"));
                profile.setBalance(profile.getBalance() + purchase.getAmount());
                seller.setBalance(seller.getBalance() - (purchase.getAmount() * (1 - FEE)));
                profileRepository.save(profile);
                profileRepository.save(seller);
                purchaseRepository.delete(purchase);
            } else
                throw new BadRequestException("Time Expired");
        } else
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    private List<PurchaseStoryDto> getPurchaseStoryDtos(Profile profile) {
        List<Purchase> purchases = purchaseRepository.findAllByProfile(profile);
        List<PurchaseStoryDto> purchaseStoryDtos = new ArrayList<>();
        for (Purchase purchase : purchases) {
            PurchaseStoryDto purchaseStoryDto = new PurchaseStoryDto(
                    purchase.getId(),
                    purchase.getOrganizationTitle(),
                    purchase.getProductTitle(),
                    purchase.getAmount(),
                    purchase.getCount(),
                    purchase.getDate()
            );
            purchaseStoryDtos.add(purchaseStoryDto);
        }

        return purchaseStoryDtos;
    }

}
