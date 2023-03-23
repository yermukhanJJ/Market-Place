package com.effective.market.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "discount_tab")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "duration", nullable = false)
    private Date date;

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
