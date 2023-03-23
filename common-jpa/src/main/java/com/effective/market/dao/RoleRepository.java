package com.effective.market.dao;

import com.effective.market.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByTitle(String title);

    Boolean existsByTitle(String title);

}
