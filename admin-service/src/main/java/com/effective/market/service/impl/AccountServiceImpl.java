package com.effective.market.service.impl;

import com.effective.market.dao.ProfileRepository;
import com.effective.market.dto.AccountDto;
import com.effective.market.dto.BalanceDto;
import com.effective.market.entity.Profile;
import com.effective.market.exception.BadRequestException;
import com.effective.market.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final ProfileRepository profileRepository;

    @Transactional(readOnly = true)
    @Override
    public List<AccountDto> getAllAccounts() {
        List<Profile> profiles = profileRepository.findAll();
        List<AccountDto> accountDtos = new ArrayList<>();
        for (Profile profile : profiles) {
            AccountDto accountDto = new AccountDto(
                    profile.getId(),
                    profile.getUsername(),
                    profile.getEmail(),
                    profile.getBalance(),
                    profile.getRoles()
            );
            accountDtos.add(accountDto);
        }
        log.info("Getting all accounts");
        return accountDtos;
    }

    @Transactional(readOnly = true)
    @Override
    public AccountDto getAccountById(Long id) {
        if (profileRepository.existsById(id)) {
            log.info("Getting account with id: " + id);
            Profile profile = profileRepository.findById(id)
                    .orElseThrow(() -> new BadRequestException("Account not found"));
            return new AccountDto(
                    profile.getId(),
                    profile.getUsername(),
                    profile.getEmail(),
                    profile.getBalance(),
                    profile.getRoles()
            );
        }
        return null;
    }

    @Transactional
    @Override
    public void deleteProfile(Long id) {
        if (profileRepository.existsById(id)) {
            profileRepository.deleteById(id);
        } else
            throw new BadRequestException("Profile with id: " + id + " not found");
    }

    @Override
    public void replenishBalance(Long id, BalanceDto balanceDto) {
        if (profileRepository.existsById(id)) {
            Profile profile = profileRepository.findById(id)
                    .orElseThrow(() -> new BadRequestException("Profile not found"));
            profile.setBalance(profile.getBalance() + balanceDto.getBalance());
            log.info("Admin replenished the balance by " + balanceDto.getBalance() + " for user " + profile.getUsername());
            profileRepository.save(profile);
        } else
            throw new BadRequestException("Profile with id: " + id + " not exists");
    }
}
