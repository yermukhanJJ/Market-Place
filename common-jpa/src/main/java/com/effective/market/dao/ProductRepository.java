package com.effective.market.dao;

import com.effective.market.entity.Organization;
import com.effective.market.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByIdProfile(Long idProfile);

    void deleteAllByOrganization(Organization organization);

    Product getByTitle(String title);

    List<Product> getAllByOrganization(Organization organization);

    @Query("select p from Product p where p.organization.enabled = true")
    List<Product> findAllByOrganizationEnabled();

    @Query("select p from Product p where p.id = ?1 and p.organization.enabled = true")
    Optional<Product> findByIdAndOrganizationEnabledIsTrue(Long id);
}
