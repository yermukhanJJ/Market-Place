package com.effective.market.dao;

import com.effective.market.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Boolean existsByTitle(String title);
    List<Organization> getAllByIdProfile(Long idProfile);
    List<Organization> getAllByEnabledTrue();
    Optional<Organization> findByTitle(String title);
    Organization getByTitle(String title);
}
