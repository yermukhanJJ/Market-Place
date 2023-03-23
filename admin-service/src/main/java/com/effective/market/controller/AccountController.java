package com.effective.market.controller;

import com.effective.market.dto.AccountDto;
import com.effective.market.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 *REST controller для операций с данными о аккаунтах
 * @author YermukhanJJ
 */
@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    /**
     * GET запрос для вывода всех аккаунтов
     * @return <b>Response code</b>: 200<br>
     * <b>Body</b>: {@link List<AccountDto>}
     */
    @GetMapping("/all")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<AccountDto>> getAll() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    /**
     * GET запрос для вывода информаций о аккаунте по id
     * @param id
     * @return <b>Response code</b>: 200<br>
     * <b>Body</b>: {@link AccountDto}
     */
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<AccountDto> getAccountById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    /**
     * DELETE запрос для удалений аккаунта по id
     * @param id
     * <b>Response code</b>: 204<br>
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable(value = "id") Long id) {
        accountService.deleteProfile(id);
    }
}
