package com.effective.market.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "organization_tab")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_profile", nullable = false)
    private Long idProfile;

    @Column(name = "title", nullable = false, length = 100, unique = true)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

}
