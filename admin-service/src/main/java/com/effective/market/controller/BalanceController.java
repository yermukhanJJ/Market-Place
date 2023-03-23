package com.effective.market.controller;

import com.effective.market.dto.BalanceDto;
import com.effective.market.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * REST controller для операций с балансом пользователей
 * @author YermukhanJJ
 */
@RestController
@RequestMapping("/api/balance")
@RequiredArgsConstructor
public class BalanceController {

    private final AccountService accountService;

    /**
     * POST запрос для пополнений баланса
     * @param id
     * @param balanceDto
     * <b>Response code</b>: 200<br>
     */
    @PostMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void replenishBalance(@PathVariable(value = "id") Long id,
                                 @RequestBody @Valid BalanceDto balanceDto) {
        accountService.replenishBalance(id, balanceDto);
    }
}
