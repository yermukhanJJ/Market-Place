package com.effective.market.dao;

import com.effective.market.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedBackRepository extends JpaRepository<Feedback, Long> {
    Integer countFeedbackByIdProduct(Long idProduct);

    @Query("select sum (f.score) from Feedback f where f.idProduct = ?1")
    Integer sumScoreByIdProduct(Long idProduct);

    List<Feedback> findAllByIdProduct(Long idProduct);
}
