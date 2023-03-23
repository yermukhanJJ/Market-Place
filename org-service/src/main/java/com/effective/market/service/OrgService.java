package com.effective.market.service;

import com.effective.market.dao.OrganizationRepository;
import com.effective.market.dao.ProfileRepository;
import com.effective.market.dto.OrgCreateDto;
import com.effective.market.dto.OrgDto;
import com.effective.market.entity.Organization;
import com.effective.market.entity.Profile;
import com.effective.market.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для управлений организациями
 *
 * @author YermukhanJJ
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrgService {

    private final OrganizationRepository organizationRepository;
    private final ProfileRepository profileRepository;

    /**
     * Метод для созданий организаций
     *
     * @param orgCreateDto
     * @param principal
     */
    public void createOrgRequest(OrgCreateDto orgCreateDto, Principal principal) {
        if (!organizationRepository.existsByTitle(orgCreateDto.getTitle())) {
            Profile profile = profileRepository.findByUsername(principal.getName())
                    .orElseThrow(() -> new BadRequestException("Username not found"));
            Organization organization = new Organization();
            organization.setIdProfile(profile.getId());
            organization.setTitle(orgCreateDto.getTitle());
            organization.setDescription(orgCreateDto.getDescription());
            organization.setEnabled(false);
            organizationRepository.save(organization);
            log.info("Add new Organization: " + organization.getTitle() + " Author: " + principal.getName());
        } else {
            throw new BadRequestException("Organization with title: " + orgCreateDto.getTitle() + " already exists");
        }
    }

    /**
     * Вывод организаций созданных пользователям
     *
     * @param principal
     * @return {@link List<OrgDto>}
     */
    public List<OrgDto> getMyOrgs(Principal principal) {
        Profile profile = profileRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new BadRequestException("Username not found"));
        List<Organization> organizations = organizationRepository.getAllByIdProfile(profile.getId());
        List<OrgDto> orgDtos = new ArrayList<>();
        for (Organization organization : organizations) {
            OrgDto orgDto = new OrgDto();
            orgDto.setTitle(organization.getTitle());
            orgDto.setDescription(organization.getDescription());
            if (Boolean.TRUE.equals(organization.getEnabled())) {
                orgDto.setStatus("Request approved");
            } else {
                orgDto.setStatus("Request not approved");
            }
            orgDtos.add(orgDto);
        }
        log.info("Getting organizations with Profile: " + principal.getName());
        return orgDtos;
    }

    /**
     * Вывод всех организаций
     *
     * @return {@link List<Organization>}
     */
    public List<Organization> getAllOrg() {
        log.info("Getting all organization");
        return organizationRepository.findAll();
    }

    /**
     * Вывод организаций которые получили одобрение
     *
     * @return {@link List<Organization>}
     */
    public List<Organization> getAllEnabledOrg() {
        return organizationRepository.getAllByEnabledTrue();
    }

    /**
     * Дать одобрение на организацию
     *
     * @param id
     */
    public void approve(Long id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Organization with id: " + id + " not found"));
        organization.setEnabled(true);
        log.info("Admin approved Organization:" + organization.getTitle());
        organizationRepository.save(organization);
    }

    /**
     * Удаление организаций по id
     *
     * @param id
     */
    public void delOrg(Long id) {
        if (!organizationRepository.existsById(id))
            throw new BadRequestException("Organization with id: " + id + " not found");
        log.info("Admin deleted Organization");
        organizationRepository.deleteById(id);
    }

    /**
     * Заморозить организацию
     *
     * @param id
     */
    public void freezeOrg(Long id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Organization not found"));
        organization.setEnabled(false);
        organizationRepository.save(organization);
        log.info("Admin frozen Organization: " + organization.getTitle());
    }
}
