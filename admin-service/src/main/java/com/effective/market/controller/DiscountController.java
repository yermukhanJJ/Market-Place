package com.effective.market.controller;

import com.effective.market.dto.DiscountCreateDto;
import com.effective.market.dto.RequestOrganizationDto;
import com.effective.market.dto.RequestProductDto;
import com.effective.market.entity.Discount;
import com.effective.market.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * REST controller для операций над скидками
 * @author YermukhanJJ
 */
@RestController
@RequestMapping("/api/discount")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountService discountService;

    /**
     *
     * @return <b>Response code</b>: 200<br>
     * <b>Body</b>: {@link List<Discount>}
     */
    @GetMapping("/all")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<Discount>> getDiscountById() {
        return ResponseEntity.ok().body(discountService.getAllDiscounts());
    }

    /**
     *
     * @param discountCreateDto
     * <b>Response code</b>: 201<br>
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createDiscount(@RequestBody @Valid DiscountCreateDto discountCreateDto) {
        discountService.createDiscount(discountCreateDto);
    }

    /**
     *
     * @param discountCreateDto
     * @param id
     * <b>Response code</b>: 200<br>
     */
    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateDiscount(@RequestBody @Valid DiscountCreateDto discountCreateDto,
                               @PathVariable(value = "id") Long id) {
        discountService.updateDiscount(id, discountCreateDto);
    }

    /**
     *
     * @param requestProductDto
     * @param id
     * <b>Response code</b>: 200<br>
     */
    @PutMapping("/for-products/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void discountForProducts(@RequestBody @Valid RequestProductDto requestProductDto,
                                    @PathVariable(value = "id") Long id) {
        discountService.addDiscountInfoToProducts(id, requestProductDto);
    }

    /**
     *
     * @param requestOrganizationDto
     * @param id
     * <b>response code</b>: 200<br>
     */
    @PutMapping("/for-organizations/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void discountForOrganizations(@RequestBody @Valid RequestOrganizationDto requestOrganizationDto,
                                         @PathVariable(value = "id") Long id) {
        discountService.addDiscountInfoToOrganizations(id, requestOrganizationDto);
    }


}
