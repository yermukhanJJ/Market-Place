package com.effective.market.service;

import com.effective.market.dao.KeyWordRepository;
import com.effective.market.dao.OrganizationRepository;
import com.effective.market.dao.ProductRepository;
import com.effective.market.dao.ProfileRepository;
import com.effective.market.dto.CreateProductDto;
import com.effective.market.dto.ProductDto;
import com.effective.market.entity.*;
import com.effective.market.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для управлений товарами
 *
 * @author YermukhanJJ
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProfileRepository profileRepository;
    private final ProductRepository productRepository;
    private final OrganizationRepository organizationRepository;
    private final KeyWordRepository keyWordRepository;

    /**
     * Добавление товара
     *
     * @param productDto
     * @param principal
     */
    public void addProduct(CreateProductDto productDto, Principal principal) {
        Profile profile = profileRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new BadRequestException("Username not found"));
        Organization organization = organizationRepository.findByTitle(productDto.getOrganizationTitle())
                .orElseThrow(() -> new BadRequestException("Organization not found"));
        if (Boolean.TRUE.equals(organization.getEnabled()) && organization.getIdProfile().equals(profile.getId())) {
            Product product = new Product();
            product.setTitle(productDto.getTitle());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setCount(productDto.getCount());
            product.setIdProfile(profile.getId());
            product.setOrganization(organization);
            Product saveProduct = productRepository.save(product);
            List<Words> wordsList = new ArrayList<>();
            for (String word : productDto.getKeyWords()) {
                Words words = new Words();
                words.setIdProduct(saveProduct.getId());
                words.setWord(word);
                wordsList.add(words);
            }

            keyWordRepository.saveAll(wordsList);
            log.info("Add new words: " + wordsList);
            log.info("Add new product: " + product);
        } else {
            throw new BadRequestException("Organization not approved");
        }

    }

    /**
     * Вывод товаров пользователя
     *
     * @param principal
     * @return {@link ProductDto}
     */
    @Transactional(readOnly = true)
    public List<ProductDto> getAllPrincipalProducts(Principal principal) {
        Profile profile = profileRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new BadRequestException("Username not found"));
        List<Product> products = productRepository.findAllByIdProfile(profile.getId());
        return getProductDtos(products);
    }

    /**
     * Вывод всех товаров
     *
     * @return {@link List<ProductDto>}
     */
    @Transactional(readOnly = true)
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAllByOrganizationEnabled();
        if (products.isEmpty())
            return new ArrayList<>();
        else
            return getProductDtos(products);
    }

    private List<ProductDto> getProductDtos(List<Product> products) {
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : products) {
            ProductDto productDto = new ProductDto();
            List<String> words = keyWordRepository.findAllByIdProduct(product.getId());
            productDto.setId(product.getId());
            productDto.setTitle(product.getTitle());
            productDto.setDescription(product.getDescription());
            productDto.setPrice(product.getPrice());
            productDto.setCount(product.getCount());
            productDto.setOrganization(product.getOrganization());
            productDto.setScore(product.getScore());
            productDto.setKeyWords(words);
            productDto.setDiscount(product.getDiscounts());
            productDtoList.add(productDto);
        }
        return productDtoList;
    }
}
