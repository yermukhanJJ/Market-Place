package com.effective.market.service;

import com.effective.market.dto.AccountDto;
import com.effective.market.dto.BalanceDto;
import com.effective.market.entity.Profile;

import java.util.List;

/**
 * Сервис доступный только админу для манипуляций над пользовательскими аккаунтами
 * @author YermukhanJJ
 */
public interface AccountService {
    /**
     * Метод для вывода всех аккаунтов
     * @return {@link AccountDto}
     */
    List<AccountDto> getAllAccounts();

    /**
     * Метод для вывода аккаунта по id
     * @param id
     * @return {@link AccountDto}
     */
    AccountDto getAccountById(Long id);

    /**
     * Метод для удалений аккаунта по id
     * @param id
     */
    void deleteProfile(Long id);

    /**
     * Метод для пополнение баланса пользователей
     * @param id
     * @param balanceDto
     */
    void replenishBalance(Long id, BalanceDto balanceDto);
}
