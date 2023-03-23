package com.effective.market.dao;

import com.effective.market.entity.Words;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeyWordRepository extends JpaRepository<Words, Long> {

    @Query("select w.word from Words w where w.idProduct = ?1")
    List<String> findAllByIdProduct(Long idProduct);

    Boolean existsByIdProduct(Long idProduct);

    void deleteByIdProduct(Long idProduct);
}
