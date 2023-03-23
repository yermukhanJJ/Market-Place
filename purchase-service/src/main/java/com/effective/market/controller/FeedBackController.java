package com.effective.market.controller;

import com.effective.market.entity.Feedback;
import com.effective.market.model.dto.FeedBackDto;
import com.effective.market.service.FeedBackService;
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
 * REST controller для операций с отзывами
 *
 * @author YermukhanJJ
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feedback")
public class FeedBackController {

    private final FeedBackService feedBackService;

    /**
     * @param product
     * @param principal
     * @param feedBackDto <b>Response code</b>: 200<br>
     */
    @PreAuthorize("@A.isPurchased( #principal, #product)")
    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void feedbackToProduct(@RequestParam Long product,
                                  Principal principal,
                                  @RequestBody @Valid FeedBackDto feedBackDto) {
        feedBackService.feedback(principal, product, feedBackDto);
    }

    /**
     * @param product
     * @return <b>Response code</b>: 200<br>
     * <b>Body</b>: {@link List<Feedback>}
     */
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<Feedback>> getAllFeedBackByProduct(@RequestParam Long product) {
        return ResponseEntity.ok(feedBackService.getAllFeedBackByIdProduct(product));
    }
}
