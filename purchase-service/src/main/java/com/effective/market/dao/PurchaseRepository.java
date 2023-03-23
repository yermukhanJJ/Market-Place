package com.effective.market.dao;

import com.effective.market.entity.Profile;
import com.effective.market.model.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    Boolean existsByProfileAndProductTitle(Profile profile, String productTitle);

    List<Purchase> findAllByProfile(Profile profile);

    Boolean existsByProfile(Profile profile);
}
