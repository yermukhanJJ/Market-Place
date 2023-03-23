package com.effective.market.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "feedback_tab")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_product", nullable = false)
    private Long idProduct;

    @Column(name = "id_profile", nullable = false)
    private Long idProfile;

    @Column(name = "description")
    private String description;

    @Column(name = "score", nullable = false)
    private Integer score;
}
