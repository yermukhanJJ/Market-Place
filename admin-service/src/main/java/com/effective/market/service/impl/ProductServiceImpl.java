package com.effective.market.service.impl;

import com.effective.market.dao.KeyWordRepository;
import com.effective.market.dao.OrganizationRepository;
import com.effective.market.dao.ProductRepository;
import com.effective.market.dao.ProfileRepository;
import com.effective.market.dto.ProductUpdateDto;
import com.effective.market.entity.Organization;
import com.effective.market.entity.Product;
import com.effective.market.entity.Profile;
import com.effective.market.entity.Words;
import com.effective.market.exception.BadRequestException;
import com.effective.market.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final OrganizationRepository organizationRepository;
    private final ProfileRepository profileRepository;
    private final ProductRepository productRepository;
    private final KeyWordRepository keyWordRepository;

    @Transactional
    @Override
    public void updateProductInfo(Long id, ProductUpdateDto productUpdateDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Product with id: " + id + " not found"));
        Organization organization = organizationRepository.findById(productUpdateDto.getIdOrganization())
                .orElseThrow(() -> new BadRequestException("Organization not found"));
        Profile profile = profileRepository.findById(productUpdateDto.getIdProfile())
                .orElseThrow(() -> new BadRequestException("Profile not found"));
        product.setIdProfile(profile.getId());
        product.setOrganization(organization);
        product.setTitle(productUpdateDto.getTitle());
        product.setDescription(productUpdateDto.getDescription());
        product.setPrice(productUpdateDto.getPrice());
        product.setCount(productUpdateDto.getCount());
        productRepository.save(product);
        log.info("Admin updated info product with id: " + id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllProducts() {
        log.info("Getting all products");
        return productRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            if (keyWordRepository.existsByIdProduct(id)) {
                log.info("Key words with idProduct: " + id + " deleted");
                keyWordRepository.deleteByIdProduct(id);
            }
            log.info("Product with id: " + id + " deleted");
            productRepository.deleteById(id);
        } else {
            throw new BadRequestException("Product not found");
        }
    }


}
