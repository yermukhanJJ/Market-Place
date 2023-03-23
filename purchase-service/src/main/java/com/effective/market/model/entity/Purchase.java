package com.effective.market.model.entity;

import com.effective.market.entity.Profile;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchase_story_tab")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
@ToString
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "organization_title", nullable = false, length = 100)
    private String organizationTitle;

    @Column(name = "product_title", nullable = false, length = 50)
    private String productTitle;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "count", nullable = false)
    private Integer count;

    @Column(name = "date_of_purchase", nullable = false)
    private LocalDateTime date;

    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Profile profile;

}
