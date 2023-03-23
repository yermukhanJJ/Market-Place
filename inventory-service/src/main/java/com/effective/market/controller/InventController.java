package com.effective.market.controller;

import com.effective.market.dto.CreateProductDto;
import com.effective.market.dto.ProductDto;
import com.effective.market.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * REST controller для операций с товарами
 *
 * @author YermukhanJJ
 */
@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventController {

    private final ProductService productService;

    /**
     * @param createProductDto
     * @param principal
     */
    @PostMapping("/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createProduct(@RequestBody @Valid CreateProductDto createProductDto, Principal principal) {
        productService.addProduct(createProductDto, principal);
    }

    /**
     * @param principal
     * @return <b>Response code</b>: 200<br>
     * <b>Body</b>: {@link List<ProductDto>}
     */
    @GetMapping("/myproduct")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<ProductDto>> getPrincipalProducts(Principal principal) {
        return ResponseEntity.ok(productService.getAllPrincipalProducts(principal));
    }

    /**
     * @return <b>Response code</b>: 200<br>
     * <b>Body</b>: {@link List<ProductDto>}
     */
    @GetMapping("/all")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
