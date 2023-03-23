package com.effective.market.service.impl;

import com.effective.market.dao.DiscountRepository;
import com.effective.market.dao.OrganizationRepository;
import com.effective.market.dao.ProductRepository;
import com.effective.market.dto.DiscountCreateDto;
import com.effective.market.dto.RequestOrganizationDto;
import com.effective.market.dto.RequestProductDto;
import com.effective.market.entity.Discount;
import com.effective.market.entity.Organization;
import com.effective.market.entity.Product;
import com.effective.market.exception.BadRequestException;
import com.effective.market.service.DiscountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;
    private final OrganizationRepository organizationRepository;
    private final ProductRepository productRepository;

    @Override
    public void createDiscount(DiscountCreateDto discountCreateDto) {
        Discount discount = new Discount();
        discount.setAmount(discountCreateDto.getAmount());
        discount.setDate(discountCreateDto.getDate());
        log.info("Created new discount: " + discount);
        discountRepository.save(discount);
    }

    @Override
    public void updateDiscount(Long id, DiscountCreateDto discountCreateDto) {
        Discount discount = discountRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Discount with id: " + id + " not found"));
        discount.setAmount(discountCreateDto.getAmount());
        discount.setDate(discountCreateDto.getDate());
        log.info("Info updated discounts with id: " + id);
        discountRepository.save(discount);
    }

    @Override
    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    @Override
    public void addDiscountInfoToProducts(Long id, RequestProductDto requestProductDto) {
        Discount discount = discountRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Discount with id " + id + " not found"));
        List<String> products = requestProductDto.getProducts();
        List<Product> productList = new ArrayList<>();
        for (String title : products) {
            Product product = productRepository.getByTitle(title);
            if (product == null)
                continue;
            product.setDiscounts(discount);
            productList.add(product);
        }

        productRepository.saveAll(productList);
        log.info("Updated products: " + productList);
    }

    @Override
    public void addDiscountInfoToOrganizations(Long id, RequestOrganizationDto requestOrganizationDto) {
        Discount discount = discountRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Discount with id: " + id + " not found"));
        List<Product> productList = new ArrayList<>();
        for (String title : requestOrganizationDto.getOrganizations()) {
            Organization organization = organizationRepository.getByTitle(title);
            if (organization == null)
                continue;
            List<Product> products = productRepository.getAllByOrganization(organization);
            if (products.isEmpty())
                continue;
            for (Product product : products) {
                product.setDiscounts(discount);
                productList.add(product);
            }
        }

        productRepository.saveAll(productList);
        log.info("Updated products: " + productList);
    }


}
