package com.effective.market.controller;

import com.effective.market.dto.ProductUpdateDto;
import com.effective.market.entity.Product;
import com.effective.market.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * REST controller для операций над товарами
 * @author YermukhanJJ
 */
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * GET запрос для вывода товаров
     * @return <b>Response code</b>: 200<br>
     * <b>Body</b>: {@link List<Product>}
     */
    @GetMapping("/all")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    /**
     * PUT запрос для изменений данных о товарах
     * @param id
     * @param productUpdateDto
     * <b>Response code</b>: 200<br>
     */
    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateProductInfo(@PathVariable(value = "id") Long id,
                                  @RequestBody @Valid ProductUpdateDto productUpdateDto) {
        productService.updateProductInfo(id, productUpdateDto);
    }

    /**
     * DELETE запрос для удалений товара по id
     * @param id
     * <b>Response code</b>: 204<br>
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProductInfo(@PathVariable(value = "id") Long id) {
        productService.deleteProduct(id);
    }
}
