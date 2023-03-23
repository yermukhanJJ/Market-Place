package com.effective.market.controller;

import com.effective.market.model.dto.PurchaseDto;
import com.effective.market.model.dto.PurchaseStoryDto;
import com.effective.market.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * REST controller для операций с покупками
 *
 * @author YermukhanJJ
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    /**
     * @param purchaseDto
     * @param discount
     * @param principal   <b>Response code</b>: 200<br>
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void purchase(@RequestBody @Valid PurchaseDto purchaseDto,
                         @RequestParam(required = false) Long discount,
                         Principal principal) {
        purchaseService.purchase(principal, purchaseDto, discount);
    }

    /**
     * @param profile
     * @return <b>Response code:</b> 200<br>
     * <b>Body</b>: {@link List<PurchaseStoryDto>}
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<PurchaseStoryDto>> getAllPurchaseByIdProfile(@RequestParam Long profile) {
        return ResponseEntity.ok(purchaseService.getAllPurchaseByProfile(profile));
    }

    /**
     * @param principal
     * @return <b>Response code</b>: 200<br>
     * <b>Body</b>: {@link List<PurchaseStoryDto>}
     */
    @GetMapping("/mypurchase")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<PurchaseStoryDto>> getAllPurchases(Principal principal) {
        return ResponseEntity.ok(purchaseService.getAllPrincipalsPurchase(principal));
    }

    /**
     * @param purchase
     * @param principal <b>Response code</b>: 200<br>
     */
    @PostMapping("/refund")
    @ResponseStatus(value = HttpStatus.OK)
    public void refundPurchase(@RequestParam Long purchase,
                               Principal principal) {
        purchaseService.refund(purchase, principal);
    }

}
